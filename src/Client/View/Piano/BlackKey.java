package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class BlackKey extends Key {

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
