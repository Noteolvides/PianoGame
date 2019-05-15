package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveSong extends JFrame{
    private JCheckBox wannaPrivate;
    private JTextField addSongName;
    private JButton okButton;

    public SaveSong() {


        setSize(250, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    public void registerController(ActionListener c) {

    }
}
