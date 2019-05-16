package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SaveSong extends JFrame{
    private JCheckBox wannaPrivate;
    private JTextField addSongName;
    private JButton okButton;

    public SaveSong() {
        wannaPrivate = new JCheckBox("Enable Song Privacy");
        okButton = new JButton("Save Song");
        addSongName = new JTextField();

        setLayout(new GridLayout(3,1));
        add(addSongName);
        add(wannaPrivate);
        add(okButton);

        setSize(250, 300);
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
}
