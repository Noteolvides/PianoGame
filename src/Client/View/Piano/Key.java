package Client.View.Piano;

import javax.swing.*;
import java.awt.*;


/**
 * Generic class of a Key component of JPiano
 *
 * @author  Gerard
 * @author Gustavo
 * @author Neil
 * @author Jiahui
 * @author Josep
 * @version 1.0
 * @since 2019-05-16
 */
public class Key extends JPanel {

    public static final int sizeKeyWhiteX = 50;
    public static final int sizeKeyBlackX = 30;
    public static final int sizeKeyWhiteY = 200;
    public static final int sizeKeyBlackY = 120;

    private JLabel numberOfKey = new JLabel();
    private Color colorKey;

    /**
     * Initialization
     */
    public Key(){

    }

    /**
     * Initialization
     * @param color Color of the key
     * @param i Width of the key
     * @param v Height of the key
     * @param i1 x Location
     * @param i2 Y location
     */
    public Key(Color color, int i, double v, int i1, int i2) {
        numberOfKey.setForeground(color);
        setSize(i,(int)v);
        setLocation(i1,i2);
    }

    /**
     * Change the label inside the key
     * @param colorLabel color of label
     * @param text  text of the label
     */
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

    /**
     * Animation of key pressed
     */
    public void touch() {
        colorKey = getBackground();
        setBackground(Color.gray);
        revalidate();
        repaint();
    }

    /**
     * Animation of key unpressed
     */
    public void unTouch(){
        setBackground(colorKey);
        revalidate();
        repaint();
    }


    /**
     * Change de octave of the key
     * @param level level in the octave
     */
    public void goLevel(int level) {
        String str = numberOfKey.getText();
        str = str.substring(0, str.length() - 1) + level;
        numberOfKey.setText(str);
        revalidate();
        repaint();
    }
}
