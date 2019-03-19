package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class BlackKey extends Key {

    public BlackKey(int i, int levelKey) {
        char keylevel = (char) (66 + (i == 6 ? -1 : (i == 7) ? -1 : 0) + i % 6);
        String keyName = Character.toString(keylevel);
        setLabel(Color.white, keyName + "#" + levelKey);
        setBackground(Color.BLACK);
        setLocation(37 + i * sizeKeyWhiteX, 0);
        setSize(sizeKeyBlackX, sizeKeyBlackY);
    }
}
