package Client.Controller.Piano;

import Client.Controller.Controller;
import Client.View.Piano.Key;
import Client.View.View;
import Model.KeyRecord;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class PianoController {
    private View view;
    private Controller controller;
    private int actualOctave; //TODO : Revision of this atribute
    private RealtimePlayer realtimePlayer;
    private char[] keyBoardConfiguration = new char[]{'A', 'S', 'D', 'F', 'G', 'H', 'J', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'L'};//TODO : OTRO ATRIBUTO QUE DEBERIA ESTAR EN MODEL
    private static int numberOFkeys = 0;
    private HashMap<Integer,KeyRecord> keys = new HashMap<>();
    private boolean recordSong;

    public PianoController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
        initController();
    }

    private void initController() {
        view.initPianoView();

        //Implementation of listener for keys 1234560 to change Octave;
        for (int i = 0; i < 7; i++) {
            view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke(i+""), i+"");
            int finalI = i;
            view.getPianoView().getPiano().getAm().put(i+"", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    view.getPianoView().getPiano().goOctave(finalI);
                }
            });
        }
        //TODO : No funcionan los botonoes creo que lo voy a cambiar


        //To get out
        view.getPianoView().getTopOption().getExitToMenu().addActionListener(e -> {
            controller.closePiano();
            controller.openPrincipal();
        });

        //To go open a song
        view.getPianoView().getTopOption().getSelectSongInSystem().addActionListener(e -> {
            controller.closePiano();
            controller.openSong();
        });

        try {
            realtimePlayer = new RealtimePlayer();
            controlKeys();
            //Listener to record Song
            view.getPianoView().getTopOption().getRecord().addActionListener(e ->{
                recordSong = true;
                view.getPianoView().getTopOption().getRecord().setEnabled(false);
                keys.clear();
            });

            view.getPianoView().getTopOption().getSave().addActionListener(e ->{
                recordSong = false;
                view.getPianoView().getTopOption().getRecord().setEnabled(false);
                
            });
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void controlKeys() {
        final int[] activado = new int[24];
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
                        int actualValue = Integer.valueOf(str.substring(str.length() -1 , str.length())) + 2;
                        str = str.substring(0, str.length() - 1) + actualValue;
                        realtimePlayer.startNote(new Note(str));
                        if (recordSong){
                            activado[finalI] = numberOFkeys+1;
                            numberOFkeys++;
                        }
                        keys.put(numberOFkeys,new KeyRecord(str,System.currentTimeMillis()));
                    }
                }
            });
            view.getPianoView().getPiano().getAm().put(keyBoardConfiguration[i] + " released", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keys.get(activado[finalI]).setEnd(System.currentTimeMillis());
                    activado[finalI] = 0;
                    k.unTouch();
                    String str = k.getNumberOfKey().getText();
                    int actualValue = Integer.valueOf(str.substring(str.length() -1 , str.length())) + 2;
                    str = str.substring(0, str.length() - 1) + actualValue;
                    realtimePlayer.stopNote(new Note(str));
                }
            });
            i++;
        }
    }
}
