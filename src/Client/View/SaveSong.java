package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveSong extends JFrame{
    private JCheckBox wannaPrivate;
    private JTextField addSongName;
    private JButton okButton;
    private JTextArea songDescription;
    public SaveSong() {
        wannaPrivate = new JCheckBox("Enable Song Privacy");
        okButton = new JButton("Save Song");
        addSongName = new JTextField();
        songDescription = new JTextArea();

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        add(addSongName, constraints);
        constraints.gridy = 1;
        constraints.ipady = 50;
        add(songDescription, constraints);
        constraints.weightx = 0;
        constraints.gridy = 2;
        constraints.ipady = 0;
        add(wannaPrivate, constraints);
        constraints.gridy = 3;
        add(okButton, constraints);

        setSize(400, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }

    public void registerController(ActionListener c) {
        okButton.setActionCommand("SAVE-SONG");
        okButton.addActionListener(c);
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
