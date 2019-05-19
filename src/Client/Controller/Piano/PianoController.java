package Client.Controller.Piano;

import Client.Controller.Controller;
import Client.View.Piano.Key;
import Client.View.Piano.KeyConfigurationVisual;
import Client.View.Piano.SelectionOfKeys;
import Client.View.View;
import Model.ConfigurationPackage.Configuration;
import Model.ConfigurationPackage.KeyConfiguration;
import Model.KeyRecord;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Controller of piano this class implements all the controllers to the keys,playing and saving
 *
 * @version 1.0
 * @since 2019-05-16
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class PianoController {
    private View view;
    private Controller controller;
    private int actualOctave = 4;
    private RealtimePlayer realtimePlayer;
    private KeyConfiguration[] keyBoardConfiguration;
    private static int numberOFkeys = 0;
    private HashMap<Integer, KeyRecord> keys;
    private SelectionOfKeys selectionOfKeys;
    private int[] activado = new int[24];
    private boolean mute = false;
    private Thread playSongKeys;

    /**
     *
     * @param view The main view with all the subviews
     * @param controller The main Controller
     */
    public PianoController(View view, Controller controller) {
        try {
            keys = new HashMap<>();
            selectionOfKeys = new SelectionOfKeys();
            Gson gson = new Gson();
            JsonReader json;
            json = new JsonReader(new FileReader("configFiles/config.json"));
            Configuration config = gson.fromJson(json, Configuration.class);
            keyBoardConfiguration = config.getKeys();
            this.view = view;
            this.controller = controller;
            initController();
            view.getPianoView().getPiano().goOctave(actualOctave);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The Initialization of all buttons in the views
     */
    private void initController() {
        view.initPianoView();
        changeKeys();
        mapKeysToOctaves();
        muteButton();
        stopButton();
        exitToMenuButton();
        selectSongButton();
        RecordButton();
        mapKeysPiano();
    }

    /**
     * Map key function
     */
    private void mapKeysPiano() {
        try {
            realtimePlayer = new RealtimePlayer();
            controlKeys();

            //Save song
            // Here we create the string that has all the information need to play it the format is acording to JFughe string format see jfughe.org
            view.getPianoView().getTopOption().getSave().addActionListener(e -> {
                if (view.getPianoView().saveConfirmation()) {
                    view.getPianoView().getTopOption().getRecord().setEnabled(true);
                    ArrayList<KeyRecord> temporalKeys = new ArrayList<KeyRecord>(keys.values());
                    Note rest = null;//Rest if key is not presed
                    Note note = null;//Note in this time
                    StringBuilder songMidi = new StringBuilder(); //Final song
                    temporalKeys.sort(Comparator.comparingInt(KeyRecord::getId));
                    KeyRecord lastKey = null; //Last key to kwnow the time
                    boolean isFirtTime = true;
                    for (KeyRecord k : temporalKeys) {
                        if (lastKey != null) {
                            //Creation of rest if no key is presed
                            if (k.getStart() - lastKey.getEnd() > 0) {
                                rest = new Note("R");
                                rest.setDuration((double) (k.getStart() - lastKey.getEnd()) / (double) 1000);
                                songMidi.append(rest.toString());
                                songMidi.append(", ");
                            }else{
                                // if two key are played at the same time
                                songMidi.append("@"+(float)k.getStart()/(float) 1000+", ");
                            }
                        }
                        //If is firt time
                        if (isFirtTime){
                            songMidi.append("@"+(float)k.getStart()/(float) 1000+", ");
                            isFirtTime = false;
                        }
                        //In any case we have to put he key in the string
                        note = new Note(k.getKey());
                        note.setDuration((double) (k.getEnd() - k.getStart()) / (double) 1000);
                        songMidi.append(note.toString());
                        songMidi.append(", ");
                        lastKey = k;
                        view.getPianoView().getTopOption().getSave().setEnabled(false);
                    }
                    //We sennd the information
                    controller.setMidiToSave(songMidi.toString());
                    controller.openSaveSong();
                } else {
                    view.getPianoView().getTopOption().getRecord().setEnabled(true);
                    view.getPianoView().getTopOption().getSave().setEnabled(false);
                }
            });
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Record Button Initialization
     */
    private void RecordButton() {
        //Listener to record Song
        view.getPianoView().getTopOption().getRecord().addActionListener(e -> {
            view.getPianoView().getTopOption().getRecord().setEnabled(false);
            keys.clear();
            numberOFkeys = 0;
            view.getPianoView().getTopOption().getSave().setEnabled(true);
        });
    }

    /**
     * Select Song Initialization
     */
    private void selectSongButton() {
        //To go open a song
        view.getPianoView().getTopOption().getSelectSongInSystem().addActionListener(e -> {
            controller.networkSelectSong();
        });
    }

    /**
     * Exit to menu button Initialization
     */
    private void exitToMenuButton() {
        //Go back to menu
        view.getPianoView().getTopOption().getExitToMenu().addActionListener(e -> {
            controller.closePiano();
            controller.openPrincipal();
            controller.networkExitPiano();
            if (playSongKeys != null){
                playSongKeys.stop();
            }
        });
    }

    /**
     * Stop button Initialization
     */
    private void stopButton() {
        view.getPianoView().getTopOption().getStop().addActionListener(e ->{
            playSongKeys.stop();
            //Hide the notes
            for (JPanel jp:view.getPianoView().getNotes()) {
                jp.setLocation(0,1000);
            }
            view.getPianoView().revalidate();
            //Clear all the notes
            view.getPianoView().getNotes().clear();
            view.getPianoView().getTopOption().getStop().setEnabled(false);
        });
    }


    /**
     * Mute button Initialization
     */
    private void muteButton() {
        view.getPianoView().getTopOption().getMuteSoundPlaying().addActionListener(e -> {

            if (!mute) {
                mute = true;
            } else {
                mute = false;
            }
        });
    }

    /**
     * For Initialization to map keys to octaves
     */
    private void mapKeysToOctaves() {
        for (int i = 0; i < 6; i++) {
            //Map the keys to change octave
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke(keyBoardConfiguration[i].getKey()), keyBoardConfiguration[i].getKey());
            int finalI = i+1;
            //Send the information to the view
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i].getKey(), new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualOctave = finalI;
                    view.getPianoView().getPiano().goOctave(finalI);
                }
            });
        }
    }


    /**
     * Action Listener of button in order to changeKeys
     */
    private void changeKeys() {
        view.getPianoView().getTopOption().getChangeKeys().addActionListener(e ->{
            //Remove all the actual keyMapp
            for (KeyConfigurationVisual k: selectionOfKeys.getList()) {
                selectionOfKeys.remove(k);
            }
            selectionOfKeys.getList().clear();
            for (KeyConfiguration k: keyBoardConfiguration) {
                selectionOfKeys.getList().add(new KeyConfigurationVisual(k.getName(),k.getKey()));
            }
            selectionOfKeys.acctKeyConfiguration();
            selectionOfKeys.setVisible(true);
            //Get the new information and add it
            selectionOfKeys.getChange().addActionListener(e1 -> {
                int i = 0;
                for (KeyConfigurationVisual l : selectionOfKeys.getList()) {
                    if (!l.getKey().getText().isEmpty() || l.getKey().getText().charAt(0) == ' '){
                        keyBoardConfiguration[i].setKey(l.getKey().getText().charAt(0));
                    }
                    i++;
                }
                view.getPianoView().getPiano().getIm().clear();
                view.getPianoView().getPiano().getAm().clear();
                view.getPianoView().setVisible(false);
                //Recreate the screen
                view.getPianoView().dispose();
                initController();
                view.getPianoView().setVisible(true);
                showPromt();
            });
        });
    }

    private void showPromt() {
        JOptionPane.showMessageDialog(selectionOfKeys,
                "Key Configuration Changed.",
                "Information",
                JOptionPane.PLAIN_MESSAGE);
        selectionOfKeys.setVisible(false);
        view.getPianoView().revalidate();
    }

    private void controlKeys() {
        int i = 6;
        for (Key k : view.getPianoView().getPiano().getKeys()) {
            //Mapp of all the keys
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke(keyBoardConfiguration[i].getKey()), keyBoardConfiguration[i].getKey());
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke("released " + keyBoardConfiguration[i].getKey()), keyBoardConfiguration[i].getKey() + " released");
            int finalI = i - 6;
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i].getKey(), new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playKey(finalI, k);
                }
            });
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i].getKey() + " released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    unPlayKey(finalI, k);
                }
            });
            //Need a Mouse listener but only use 2 methods ... :(
            k.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    playKey(finalI, k);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    unPlayKey(finalI, k);
                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });
            i++;
        }
    }


    private void unPlayKey(int finalI, Key k) {
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


    private void playKey(int finalI, Key k) {
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

    public void playSong(String songMidi) {
        try {
            //First whe ask for song
            controller.networkRequestSong();
            //Make abaliable play the mute button
            view.getPianoView().getTopOption().getMuteSoundPlaying().setEnabled(true);
            //Create a patter to play the notes
            Pattern pattern = new Pattern(songMidi);
            //We put the notes in the screen with playerSongPiano
            PlayerSongPiano player = new PlayerSongPiano(view.getPianoView(), pattern);
            RealtimePlayer realtimePlayer2 = new RealtimePlayer();

            //Create the main thread that moves the theys in the crees each 10ms
            playSongKeys =  new Thread(() -> {
                int i = 0;
                Note n;
                while (i != view.getPianoView().getNotes().size()) {
                    for (JPanel jf : view.getPianoView().getNotes()) {
                        Point p = jf.getLocation();
                        //Increase the localization by 1
                        jf.setLocation(p.x, (int) (p.y + 1));
                        n = new Note(jf.getName());
                        //If we are in the piano play the key
                        if (n.getOctave() == actualOctave || n.getOctave() == (actualOctave + 1)) {
                            if (jf.getLocation().y + jf.getSize().height > 350 && jf.getComponents().length == 0 && jf.getLocation().y < 350) {
                                JTextField flag = new JTextField("Yes");
                                flag.setVisible(false);
                                jf.add(flag);
                                if (!mute) {
                                    realtimePlayer2.startNote(n);
                                }
                            }else{
                                jf.setVisible(true);
                            }
                        } else {
                            jf.setVisible(false);
                        }
                        // If the key has ended stop the key and hide it
                        if (jf.getComponents().length != 0 && jf.getLocation().y > 350) {
                            jf.remove(jf.getComponent(0));
                            jf.setVisible(false);
                            realtimePlayer2.stopNote(n);
                            i++;
                        }
                    }
                    view.getPianoView().revalidate();
                    view.getPianoView().repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException j) {
                        j.printStackTrace();
                    }
                }
                view.getPianoView().getTopOption().getStop().setEnabled(false);
                view.getPianoView().getNotes().clear();

            });
            playSongKeys.start();
            realtimePlayer2.close();
        } catch (MidiUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
