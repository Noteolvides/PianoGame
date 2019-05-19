package Client.View.Piano;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static Client.Network.ClientConnection.*;

/**
 * MainServer View that contains all the utilities of the piano
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class JPiano extends JFrame {

    private TopPanel topOption;
    private JPanel botOption;
    private ArrayList<JPanel> notes;
    private JPanel rightOption;
    private ViewPiano piano;

    /**
     * Initialization of JPiano
     */
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
     * Function that shows a PopUp with the information of the adding a friend.
     * @param petitionResult Type of error.
     */
    public void savePopUp(int petitionResult) {
        if (petitionResult == OK) {
            JOptionPane.showMessageDialog(this, "The song was saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        if (petitionResult == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "There was a problem with the database.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "There was a problem saving the song.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if  (petitionResult == ERROR_MIDI) {
            JOptionPane.showMessageDialog(this, "There was a problem with the midi of the song.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Promts a mesagge to the user if he wants to save a song
     * @return if wants to save it or not
     */
    public boolean saveConfirmation() {
        return JOptionPane.showConfirmDialog(this, "Are you sure do you save the song?", "Save Song", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }
}
