package Client.View;

import javax.swing.*;
import java.awt.*;

public class ViewPiano extends JLayeredPane {
    public static final int sizeKeyX =  50;
    public static final int sizeKeyWhiteY =  200;
    public static final int sizeKeyBlackY =  70;
    public static final int numberOfKeys = 14;


    public ViewPiano(){
        setSize(sizeKeyX *(numberOfKeys+1), sizeKeyWhiteY);
        setLayout(null);
        //Here we put all the white notes
        for (int i = 1; i < numberOfKeys; i++) {
            createWhiteKey(i);
        }
        //Now we have to print the black keys
        for (int i = 2; i < numberOfKeys; i++) {
            JButton blackKey = new JButton();
            blackKey.setBackground(Color.BLACK);
            blackKey.setLocation(i* sizeKeyX/2,0);
            blackKey.setSize(sizeKeyX, sizeKeyBlackY);
            add(blackKey,1);
        }
    }

    private void createWhiteKey(int i) {
        JButton whiteKey = new JButton();
        whiteKey.setBackground(Color.WHITE);
        whiteKey.setLocation(i* sizeKeyX,0);
        whiteKey.setSize(sizeKeyX, sizeKeyWhiteY);
        add(whiteKey,0);
    }
}
