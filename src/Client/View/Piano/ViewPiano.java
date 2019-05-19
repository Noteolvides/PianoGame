package Client.View.Piano;


import javax.swing.*;
import java.util.ArrayList;

/**
 * Main view of the piano, class that contains other views of piano
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class ViewPiano extends JLayeredPane {

    public static final int numberOfKeys = 14;
    private ArrayList<Key> keys = new ArrayList<>();

    private InputMap im = getInputMap(WHEN_FOCUSED);
    private ActionMap am = getActionMap();

    /**
     * Initialization
     */
    public ViewPiano() {

        setLayout(null);
        creationOfPiano(4);

    }


    /**
     * Create a piano with the select level
     * @param level
     */
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

        for (int i = 8; i <= numberOfKeys; i++) {
            createWhiteKey(i,level+1);
        }

        for (int i = 8; i < numberOfKeys; i++) {
            if (i != 10){
                createBlackKey(i,level+1);
            }
        }
    }

    /**
     * Create a new blackKey in the asignated position and level in the octave
     * @param i postition in the screen
     * @param level Level of the octave
     */
    private void createBlackKey(int i,int level) {
        BlackKey blackKey = new BlackKey(i,level);
        add(blackKey, 1, -1);
        keys.add(blackKey);
    }

    /**
     * Create a new whiteKey in the asignated position and level in the octave
     * @param i postition in the screen
     * @param level Level of the octave
     */
    private void createWhiteKey(int i,int level) {
        WhiteKey whiteKey = new WhiteKey(i,level);
        add(whiteKey, 0, -1);
        keys.add(whiteKey);
    }

    /**
     * Change the level in the piano
     * @param level level of the octave
     */
    public void goOctave(int level) {
        int i= 0;
        for (Key k : keys){
            if (i > 8){
                k.goLevel(level+1);
            }else {
                k.goLevel(level);
            }
            i++;
        }
    }

    public InputMap getIm() {
        return im;
    }

    public ActionMap getAm() {
        return am;
    }

    public ArrayList<Key> getKeys() {
        return keys;
    }
}
