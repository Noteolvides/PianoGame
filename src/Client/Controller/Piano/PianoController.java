package Client.Controller.Piano;

import Client.Controller.Controller;
import Client.View.Piano.Key;
import Client.View.View;
import Model.KeyRecord;
import Model.Song;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.midi.MidiParser;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class PianoController {
    private View view;
    private Controller controller;
    private int actualOctave = 4; //TODO : Revision of this atribute
    private RealtimePlayer realtimePlayer;
    private char[] keyBoardConfiguration = new char[]{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'L'};//TODO : OTRO ATRIBUTO QUE DEBERIA ESTAR EN MODEL
    private static int numberOFkeys = 0;
    private HashMap<Integer, KeyRecord> keys = new HashMap<>();
    private boolean recordSong;
    private PlayerSongPiano player;
    private int[] activado = new int[24];
    private boolean mute = false;

    public static void main(String[] args) {
        View v = new View();
        PianoController pianoController = new PianoController(v, new Controller(v));
    }

    public PianoController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
        initController();
    }

    private void initController() {
        view.initPianoView();
        view.getPianoView().setVisible(true);

        //Implementation of listener for keys 1234560 to change Octave;
        for (int i = 1; i < 7; i++) {
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke(i + ""), i + "");
            int finalI = i;
            view.getPianoView().getPiano().getAm().put(i + "", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualOctave = finalI;
                    view.getPianoView().getPiano().goOctave(finalI);
                }
            });
        }

        view.getPianoView().getTopOption().getMuteSoundPlaying().addActionListener(e -> {
            if (!mute){
                mute = true;
            }else{
                mute = false;
            }
        });


        //To get out
        view.getPianoView().getTopOption().getExitToMenu().addActionListener(e -> {
            controller.closePiano();
            controller.networkExitPiano();
            controller.openPrincipal();
        });

        //To go open a song
        view.getPianoView().getTopOption().getSelectSongInSystem().addActionListener(e -> {
            controller.closePiano();
            controller.networkSelectSong();
            controller.openSong();
        });

        view.getPianoView().getTopOption().getPlay().addActionListener(e -> {
            try {
                view.getPianoView().getTopOption().getMuteSoundPlaying().setEnabled(true);
                Pattern pattern = MidiFileManager.loadPatternFromMidi(new File("Song.mid"));
                player = new PlayerSongPiano(view.getPianoView(), pattern);
                Player play = new Player();
                RealtimePlayer realtimePlayer2 = new RealtimePlayer();

                new Thread(() -> {
                    double time = 0;
                    int i = 0;
                    Note n;
                    boolean flag[] = new boolean[view.getPianoView().getNotes().size()];
                    while (10 * time < 360 + (-player.getY())) {
                        i = 0;
                        for (JPanel jf : view.getPianoView().getNotes()) {
                            Point p = jf.getLocation();
                            jf.setLocation(p.x, (int) (p.y + 10));
                            n = new Note(jf.getName());
                            if (n.getOctave() == actualOctave || n.getOctave() == (actualOctave + 1)) {
                                if (p.y + 10 + jf.getSize().height > 330 && !flag[i]) {
                                    flag[i] = true;
                                    if (!mute){
                                        realtimePlayer2.startNote(n);
                                    }
                                } else {
                                    jf.setVisible(true);
                                }
                            } else {
                                jf.setVisible(false);
                            }
                            if (flag[i] && p.y + 10 > 330) {
                                realtimePlayer2.stopNote(n);
                            }
                            i++;
                        }
                        view.getPianoView().revalidate();
                        view.getPianoView().repaint();
                        try {
                            time++;
                            Thread.sleep(100);
                        } catch (InterruptedException j) {
                            j.printStackTrace();
                        }
                    }

                }).start();
                realtimePlayer2.close();
            } catch (IOException | InvalidMidiDataException | MidiUnavailableException ex) {
                ex.printStackTrace();
            }
        });

        try {
            realtimePlayer = new RealtimePlayer();
            controlKeys();

            //Listener to record Song
            view.getPianoView().getTopOption().getRecord().addActionListener(e -> {
                recordSong = true;
                view.getPianoView().getTopOption().getRecord().setEnabled(false);
                keys.clear();
                view.getPianoView().getTopOption().getSave().setEnabled(true);
            });

            //Save song
            view.getPianoView().getTopOption().getSave().addActionListener(e -> {
                if (view.getPianoView().saveConfirmation()) {
                    recordSong = false;
                    view.getPianoView().getTopOption().getRecord().setEnabled(true);
                    ArrayList<KeyRecord> temporalKeys = new ArrayList<KeyRecord>(keys.values());
                    Note rest = null;
                    Note note = null;
                    StringBuilder song = new StringBuilder();
                    temporalKeys.sort(Comparator.comparingInt(KeyRecord::getId));
                    KeyRecord lastKey = null;
                    for (KeyRecord k : temporalKeys) {
                        if (lastKey != null) {
                            if (k.getStart() - lastKey.getEnd() > 0) {
                                rest = new Note("R");
                                rest.setDuration((double) (k.getStart() - lastKey.getEnd()) / (double) 1000);
                                song.append(rest.toString());
                                song.append(", ");
                            }
                        }
                        note = new Note(k.getKey());
                        note.setDuration((double) (k.getEnd() - k.getStart()) / (double) 1000);
                        song.append(note.toString());
                        song.append(", ");
                        lastKey = k;
                    }
                /*
                //TODO: Delete THIS
                    Song songD = new Song();
                //
                controller.setSongToSave(song.toString(), songD);
                controller.networkSaveSong(song.toString());
                */

                    try {
                        System.out.println(song.toString());
                        MidiFileManager
                                .savePatternToMidi(new Pattern(song.toString()), new File("Song.mid"));
                    } catch (IOException ex) {

                    }
                }
                // HASTA AQUI
            });
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void controlKeys() {
        int i = 0;
        for (Key k : view.getPianoView().getPiano().getKeys()) {
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke(keyBoardConfiguration[i]), keyBoardConfiguration[i]);
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke("released " + keyBoardConfiguration[i]), keyBoardConfiguration[i] + " released");
            int finalI = i;
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i], new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (activado[finalI] == 0) {
                        k.touch();
                        String str = k.getNumberOfKey().getText();
                        int actualValue = Integer.valueOf(str.substring(str.length() - 1, str.length()));
                        str = str.substring(0, str.length() - 1) + actualValue;
                        realtimePlayer.startNote(new Note(str));
                        activado[finalI] = numberOFkeys + 1;
                        numberOFkeys++;
                        keys.put(numberOFkeys, new KeyRecord(str, realtimePlayer.getCurrentTime(), numberOFkeys));
                    }
                }
            });
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i] + " released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (keys.get(activado[finalI]) != null) {
                        keys.get(activado[finalI]).setEnd(realtimePlayer.getCurrentTime());
                        activado[finalI] = 0;
                        k.unTouch();
                        String str = k.getNumberOfKey().getText();
                        int actualValue = Integer.valueOf(str.substring(str.length() - 1, str.length()));
                        str = str.substring(0, str.length() - 1) + actualValue;
                        realtimePlayer.stopNote(new Note(str));
                    }
                }
            });
            i++;
        }
    }
}
