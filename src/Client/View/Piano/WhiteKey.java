package Client.View.Piano;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class WhiteKey extends Key {

    public WhiteKey(int i, int levelKey) {
        char keylevel = (char) (66 + (i == 6 ? -1 : (i == 7) ? -1 : 0) + i % 6);
        String keyName = Character.toString(keylevel);
        setLabel(Color.BLACK, keyName + levelKey);
        setBackground(Color.white);
        setLocation(i * sizeKeyWhiteX, 50);
        setSize(sizeKeyWhiteX, sizeKeyWhiteY);
        setBorder(new BevelBorder(BevelBorder.RAISED));
    }
}
