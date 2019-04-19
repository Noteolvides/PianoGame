package Client.View.Piano;

import javax.swing.*;
import java.util.ArrayList;

public class ViewPiano extends JLayeredPane {

    public static final int numberOfKeys = 14;
    private ArrayList<Key> keys = new ArrayList<>();

    public ViewPiano() {

        setLayout(null);
        creationOfPiano(0);

    }

    private void creationOfPiano(int level) {
        //Here we put all the white notes
        for (int i = 1; i <= numberOfKeys/2; i++) {
            createWhiteKey(i,level);
        }

        //Now we have to print the black keys and exclude the ones that does not need to be there.
        for (int i = 1; i < numberOfKeys/2; i++) {
            if (i != 3){
                createBlackKey(i,level);
            }
        }

        for (int i = 1; i <= numberOfKeys; i++) {
            createWhiteKey(i,level+1);
        }

        for (int i = 8; i < numberOfKeys; i++) {
            if (i != 10){
                createBlackKey(i,level+1);
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

    public void goOctave(int level) {
        for (Key k : keys){
            this.remove(k);
        }
        keys.clear();
        creationOfPiano(level);
        revalidate();
        repaint();
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }
}
