package Client.View.Piano;

import javax.swing.*;
import java.awt.*;

public class JPiano extends JFrame {

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
        leftOption.setSize(150,100);
        leftOption.setLocation(50*(ViewPiano.numberOfKeys+2)/2-70,220);
        add(leftOption);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),330);
        setVisible(true);
    }

    public TopPanel getTopOption() {
        return topOption;
    }

    public ViewPiano getPiano() {
        return piano;
    }

    public LeftPanel getLeftOption() {
        return leftOption;
    }

}
