package Client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * View for registering a song, when a user wants to
 * save his/her song that hsa just been recorded
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
public class SaveSong extends JFrame{
    //View for saving a song, attributes
    private JCheckBox wannaPrivate;
    private JTextField addSongName;
    private JButton okButton;
    private JTextArea songDescription;
    private JLabel songName;
    private JLabel songDesc;

    /**
     * Constructor, it creates and adds all the jComponents and adds them to a jPanel. (Bassically it have all the initialization of SaveSong frame)
     */
    public SaveSong() {
        //Adding all the labels and names of the attributes
        wannaPrivate = new JCheckBox("Enable Song Privacy");
        okButton = new JButton("Save Song");
        addSongName = new JTextField();
        songDescription = new JTextArea();
        songDescription.setBorder(BorderFactory.createLineBorder(Color.RED));
        songDesc = new JLabel("Add Description");
        songName = new JLabel("Add Song Title");


        //Adding all the panels and boxes to the main frame with a GridBagLayout
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

        //Frame attributes
        setSize(400, 300);
        setTitle("Register");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setVisible(false);
    }


    /**
     * Register the correspondent controller to the view
     */
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
