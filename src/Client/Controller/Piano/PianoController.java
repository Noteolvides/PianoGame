package Client.Controller.Piano;

import Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        
    }
}
