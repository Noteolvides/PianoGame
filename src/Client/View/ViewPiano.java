package Client.View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class ViewPiano extends JLayeredPane {

    public static final int sizeKeyWhiteX = 50;
    public static final int sizeKeyBlackX = 30;
    public static final int sizeKeyWhiteY = 200;
    public static final int sizeKeyBlackY = 120;
    public static final int numberOfKeys = 14;
    private ArrayList<JButton> keys = new ArrayList<>();

    public ViewPiano() {
        setLayout(null);

        //Here we put all the white notes
        for (int i = 1; i <= numberOfKeys; i++) {
            createWhiteKey(i);
        }
        //Now we have to print the black keys and exclude the ones that does not need to be there.
        for (int i = 1; i < numberOfKeys; i++) {
            if (!(i == 3 || i == 7 || i == 10)){
                createBlackKey(i);
            }
        }
    }

    private void createBlackKey(int i) {
        JButton blackKey = new JButton();
        blackKey.setBackground(Color.BLACK);
        blackKey.setLocation(37 + i * sizeKeyWhiteX, 0);
        blackKey.setSize(sizeKeyBlackX, sizeKeyBlackY);
        add(blackKey, 1, -1);
        keys.add(blackKey);
    }

    private void createWhiteKey(int i) {
        JButton whiteKey = new JButton();
        whiteKey.setBackground(Color.WHITE);
        whiteKey.setLocation(i * sizeKeyWhiteX, 0);
        whiteKey.setSize(sizeKeyWhiteX, sizeKeyWhiteY);
        whiteKey.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(whiteKey, 0, -1);
        keys.add(whiteKey);
    }
}
