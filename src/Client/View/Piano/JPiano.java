package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class JPiano extends JFrame {
    public static void main(String[] args) {
        JPiano jp = new JPiano();
    }

    private TopPanel topOption;
    private JPanel botOption;
    private JPanel leftOption;
    private JPanel rightOption;
    private ViewPiano piano;
    public JPiano() {
        super("Piano");

        setLayout(new BorderLayout());

        piano = new ViewPiano();
        add(piano,BorderLayout.CENTER);

        topOption = new TopPanel();
        add(topOption,BorderLayout.NORTH);


        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),500);
        setVisible(true);
    }
}
