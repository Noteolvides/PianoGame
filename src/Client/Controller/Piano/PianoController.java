package Client.Controller.Piano;

import Client.View.Piano.Key;
import Client.View.View;
import com.sun.glass.events.KeyEvent;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class PianoController {
    private View view;
    private int actualOctave; //TODO : Revision of this atribute

    public static void main(String[] args) {
        View view = new View();
        PianoController pianoController = new PianoController(view);
    }

    public PianoController(View view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.initPianoView();
            view.getPianoView().getLeftOption().getNextOctave().addActionListener(e -> {
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


        view.getPianoView().getPiano().getKeys().get(0).getIm().put(KeyStroke.getKeyStroke((char) KeyEvent.VK_A),"a");
        view.getPianoView().getPiano().getKeys().get(0).getAm().put("A", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hi");
            }
        });

    }
}
