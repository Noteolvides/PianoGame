package Client.View.Piano;

import java.awt.*;

/**
 * BlackKey is a extends of a Key to show a blackKey in the pianoView
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
public class BlackKey extends Key {

    /**
     * Constructor of blackKey
     * @param i        The number in that octave
     * @param levelKey The octave that this key represents
     */
    public BlackKey(int i, int levelKey) {
        int iCopy = i;
        if (i >= 8){
            iCopy = i%7;
        }
        char keylevel = (char) (66 + (iCopy == 6 ? -1 : (iCopy == 7) ? -1 : 0) + iCopy % 6);
        String keyName = Character.toString(keylevel);
        setLabel(Color.white, keyName + "#" + levelKey);
        setBackground(Color.BLACK);
        setLocation(37 + i * sizeKeyWhiteX, 50);
        setSize(sizeKeyBlackX, sizeKeyBlackY);
    }
}
