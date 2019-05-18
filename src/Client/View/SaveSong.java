package Client.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static Client.Network.ClientConnection.*;

public class SaveSong extends JFrame{
    private JCheckBox wannaPrivate;
    private JTextField addSongName;
    private JButton okButton;
    private JTextArea songDescription;
    private JLabel songName;
    private JLabel songDesc;

    public SaveSong() {
        wannaPrivate = new JCheckBox("Enable Song Privacy");
        okButton = new JButton("Save Song");
        addSongName = new JTextField();
        songDescription = new JTextArea();
        songDescription.setBorder(BorderFactory.createLineBorder(Color.RED));

        songDesc = new JLabel("Add Description");
        songName = new JLabel("Add Song Title");


        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(songName, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.weightx = 1;
        add(addSongName, constraints);
        constraints.gridy = 2;
        add(songDesc, constraints);
        constraints.gridy = 3;
        constraints.ipady = 50;
        add(songDescription, constraints);
        constraints.weightx = 0;
        constraints.gridy = 4;
        constraints.ipady = 0;
        add(wannaPrivate, constraints);
        constraints.fill = 0;
        constraints.gridy = 5;
        add(okButton, constraints);

        setSize(400, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    public void registerController(ActionListener c) {
        okButton.setActionCommand("SAVE-SONG");
        okButton.addActionListener(c);
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

    public JCheckBox getWannaPrivate() {
        return wannaPrivate;
    }

    public void setWannaPrivate(JCheckBox wannaPrivate) {
        this.wannaPrivate = wannaPrivate;
    }

    public JTextField getAddSongName() {
        return addSongName;
    }

    public void setAddSongName(JTextField addSongName) {
        this.addSongName = addSongName;
    }

    public JButton getOkButton() {
        return okButton;
    }

    public void setOkButton(JButton okButton) {
        this.okButton = okButton;
    }

    public JTextArea getSongDescription() {
        return songDescription;
    }
}
