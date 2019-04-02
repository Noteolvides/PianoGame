package Client.View.Piano;

import javax.swing.*;
import java.util.ArrayList;

public class ViewPiano extends JLayeredPane {

    public static final int numberOfKeys = 7;
    private ArrayList<Key> keys = new ArrayList<>();

    public ViewPiano() {

        setLayout(null);

        //Here we put all the white notes
        for (int i = 1; i <= numberOfKeys; i++) {
            createWhiteKey(i,1);
        }
        //Now we have to print the black keys and exclude the ones that does not need to be there.
        for (int i = 1; i < numberOfKeys; i++) {
            if (i != 3){
                createBlackKey(i,1);
            }
        }
    }

    private void createBlackKey(int i,int level) {
        BlackKey blackKey = new BlackKey(i,level);
        add(blackKey, 1, -1);
        keys.add(blackKey);
    }

    private void createWhiteKey(int i,int level) {
        WhiteKey whiteKey = new WhiteKey(i,level);
        add(whiteKey, 0, -1);
        keys.add(whiteKey);
    }
}
