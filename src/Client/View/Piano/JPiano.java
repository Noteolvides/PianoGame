package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Main View that contains all the utilities of the piano
 *
 * @author  Gerard
 * @author Gustavo
 * @author Neil
 * @author Jiahui
 * @author Josep
 * @version 1.0
 * @since 2019-05-16
 */
public class JPiano extends JFrame {

    private TopPanel topOption;
    private JPanel botOption;
    private ArrayList<JPanel> notes;
    private JPanel rightOption;
    private ViewPiano piano;


    public JPiano() {
        super("Piano");
        notes = new ArrayList<>();
        setLayout(null);

        piano = new ViewPiano();
        piano.setLocation(0,300);
        piano.setSize(50*(ViewPiano.numberOfKeys+2),330);
        add(piano);

        topOption = new TopPanel();
        topOption.setSize(50*(ViewPiano.numberOfKeys+2),30);
        add(topOption,BorderLayout.NORTH);

        JPanel dontShow = new JPanel();
        dontShow.setSize(50*(ViewPiano.numberOfKeys+2),50);
        dontShow.setLocation(10,550);
        dontShow.setBackground(this.getBackground());

        add(dontShow);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),630);
        setVisible(false);
    }

    public TopPanel getTopOption() {
        return topOption;
    }

    public ViewPiano getPiano() {
        return piano;
    }


    public ArrayList<JPanel> getNotes() {
        return notes;
    }

    /**
     * Promts a mesagge to the user if he wants to save a song
     * @return if wants to save it or not
     */
    public boolean saveConfirmation() {
        return JOptionPane.showConfirmDialog(this, "Are you sure do you save the song?", "Save Song", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
