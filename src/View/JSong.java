package View;

import javax.swing.*;

public class JSong {
    //Basic elements of the JSongs Class
    //This is the View where we are going to show the songs
    private JList songsList;
    //This is the View that allows us to play a concrete song
    private JButton playButton;


    public JList getSongsList() {
        return songsList;
    }

    public void setSongsList(JList songsList) {
        this.songsList = songsList;
    }

    public JButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(JButton playButton) {
        this.playButton = playButton;
    }
}
