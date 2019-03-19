package Client.View.Piano;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class ViewPiano extends JLayeredPane {

    public static final int numberOfKeys = 7;
    private ArrayList<Key> keys = new ArrayList<>();

    public ViewPiano() {

        setLayout(null);

        //Here we put all the white notes
        for (int i = 1; i <= numberOfKeys; i++) {
            createWhiteKey(i);
        }
        //Now we have to print the black keys and exclude the ones that does not need to be there.
        for (int i = 1; i < numberOfKeys; i++) {
            if (i != 3){
                createBlackKey(i);
            }
        }
    }

    private void createBlackKey(int i) {
        BlackKey blackKey = new BlackKey(i,1);
        add(blackKey, 1, -1);
        keys.add(blackKey);
    }

    private void createWhiteKey(int i) {
        WhiteKey whiteKey = new WhiteKey(i,1);
        add(whiteKey, 0, -1);
        keys.add(whiteKey);
    }
}
