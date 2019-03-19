package Client.View;

import Client.View.Piano.ViewPiano;

import javax.swing.*;
import java.awt.*;

public class JPiano extends JFrame {
    public static void main(String[] args) {
        JPiano jp = new JPiano();
    }

    private JPanel topOption;
    private JPanel botOption;
    private JPanel leftOption;
    private JPanel rightOption;
    private ViewPiano piano;
    public JPiano() {
        super("Piano");
        setLayout(new BorderLayout());
        piano = new ViewPiano();
        add(piano,BorderLayout.CENTER);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),500);
        setVisible(true);
    }
}
