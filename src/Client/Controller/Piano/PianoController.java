package Client.Controller.Piano;

import Client.Controller.Controller;
import Client.View.Piano.Key;
import Client.View.View;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.theory.Note;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PianoController {
    private View view;
    private Controller controller;
    private int actualOctave; //TODO : Revision of this atribute
    private RealtimePlayer realtimePlayer;
    private char[] keyBoardConfiguration = new char[]{'A','S','D','F','G','H','J','Z','X','C','V','B','N','M','Q','W','E','R','T','Y','U'};//TODO : OTRO ATRIBUTO QUE DEBERIA ESTAR EN MODEL
    private boolean[] isPresed = new boolean[24];
    public static void main(String[] args) {
        //View view = new View();
        //PianoController pianoController = new PianoController(view, controller);
    }

    public PianoController(View view, Controller controller) {
        this.view = view;
        this.controller = controller;
        initController();
    }

    private void initController() {
        view.initPianoView();
            view.getPianoView().getLeftOption().getNextOctave().addActionListener(e -> {
                view.getPianoView().getPiano().getKeys().get(0).touch();
                if (actualOctave > 0){
                    actualOctave--;
                    view.getPianoView().getPiano().goOctave(actualOctave);
                }
        });
        view.getPianoView().getLeftOption().getPrevOctave().addActionListener(e -> {
            if (actualOctave < 6){
                actualOctave++;
                view.getPianoView().getPiano().goOctave(actualOctave);
            }
        });
        view.getPianoView().getTopOption().getExitToMenu().addActionListener(e -> {
            controller.closePiano();
            controller.openPrincipal();
        });
        view.getPianoView().getTopOption().getSelectSongInSystem().addActionListener(e -> {
            controller.closePiano();
            controller.openSong();
        });

        try {
            realtimePlayer = new RealtimePlayer();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }

        final boolean[] activado = new boolean[24];
        
        view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke("A"), "A");
        view.getPianoView().getPiano().getIm().put(KeyStroke.getKeyStroke("released A"), "A released");
        view.getPianoView().getPiano().getAm().put("A", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
					    if (!activado[0]){
                            view.getPianoView().getPiano().getKeys().get(0).touch();
                            view.getPianoView().getPiano().rePaint();
                            realtimePlayer.startNote(new Note("A"));
					        activado[0] = true;
						}
					}
				});

        view.getPianoView().getPiano().getAm().put("A released", new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
					    activado[0] = false;
                        view.getPianoView().getPiano().getKeys().get(0).unTouch();
						realtimePlayer.stopNote(new Note("A"));
					}
				});
    }
}
