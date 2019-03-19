package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class JPiano extends JFrame {
    public static void main(String[] args) {
        JPiano jp = new JPiano();
    }

    private TopPanel topOption;
    private JPanel botOption;
    private LeftPanel leftOption;
    private JPanel rightOption;
    private ViewPiano piano;
    public JPiano() {
        super("Piano");

        setLayout(null);

        piano = new ViewPiano();
        piano.setSize(50*(ViewPiano.numberOfKeys+2),500);
        add(piano);

        topOption = new TopPanel();
        topOption.setSize(50*(ViewPiano.numberOfKeys+2),30);
        add(topOption,BorderLayout.NORTH);

        leftOption = new LeftPanel();
        leftOption.setSize(50,100);
        leftOption.setLocation(50*(ViewPiano.numberOfKeys+2)-50,120);
        add(leftOption,BorderLayout.EAST);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),300);
        setVisible(true);
    }
}
