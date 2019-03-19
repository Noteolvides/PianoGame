package View;

import javax.swing.*;

public class JSong extends JPanel{
    //Basic elements of the JSongs Class
    //This is the View where we are going to show the songs
    private JList songsList;
    //This is the View that allows us to play a concrete song
    private JButton playButton;
    private JButton backButton;
    //This is the text element where we are going to show the different songs
    private JTextArea titlePanel;

    public JSong () {
        //Inicialization of Layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        songsList = new JList ();
        playButton = new JButton ();
        backButton = new JButton ();
        titlePanel = new JTextArea();

        //We put all this elements in the JPanel
        add(songsList);
        add(playButton);
        add(backButton);

    }

    public void makePanelVisible () {
        setVisible(true);
    }

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

    public JButton getBackButton() {
        return backButton;
    }
    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }
}
