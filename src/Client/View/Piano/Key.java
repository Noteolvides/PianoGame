package Client.View.Piano;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Key extends JPanel {

    public static final int sizeKeyWhiteX = 50;
    public static final int sizeKeyBlackX = 30;
    public static final int sizeKeyWhiteY = 200;
    public static final int sizeKeyBlackY = 120;

    private JLabel numberOfKey = new JLabel();

    public void setLabel(Color colorLabel,String text){
        setLayout(new BorderLayout());
        numberOfKey.setText(text);
        numberOfKey.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfKey.setForeground(colorLabel);
        add(numberOfKey,BorderLayout.SOUTH);
    }

    public JLabel getNumberOfKey() {
        return numberOfKey;
    }
}
