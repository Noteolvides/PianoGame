package Client.View;

import javax.swing.*;
import java.awt.*;

public class JPiano extends JFrame {
    private JPanel topOption;
    private JPanel botOption;
    private JPanel leftOption;
    private JPanel rightOption;
    private ViewPiano piano;
    public JPiano() {
        super("Piano");
        setLayout(new BorderLayout());


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }
}
