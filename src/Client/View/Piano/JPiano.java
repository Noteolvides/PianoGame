package Client.View.Piano;

import Client.Controller.Piano.PlayerSongPiano;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class JPiano extends JFrame {

    private TopPanel topOption;
    private JPanel botOption;
    //private LeftPanel leftOption;
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

        //leftOption = new LeftPanel();
        //leftOption.setSize(150,100);
        //leftOption.setLocation(50*(ViewPiano.numberOfKeys+2)/2-70,220);
        //add(leftOption);

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(50*(ViewPiano.numberOfKeys+2),630);
        setVisible(false);
    }

    public TopPanel getTopOption() {
        return topOption;
    }

    public ViewPiano getPiano() {
        return piano;
    }

    //public LeftPanel getLeftOption() {
    //    return leftOption;
    //}


    public ArrayList<JPanel> getNotes() {
        return notes;
    }
}
