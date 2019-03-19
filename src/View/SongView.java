package View;

import javax.swing.*;

public class SongView extends JPanel {
    //This is the View that allows us to show an icon of the music
    private ImageIcon musicIcon;
    //This is the View that allows us to play a concrete song
    private JButton playButton;
    private ImageIcon informationIcon;

    public SongView () {
        playButton = new JButton ();
        add(playButton);
    }
}
