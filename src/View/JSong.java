package View;

import View.Images.SongPrueba;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class JSong extends JPanel{
    //Basic elements of the JSongs Class
    //This is the View where we are going to show the songs
    private ArrayList<SongView> songsList;
    private JButton backButton;
    //This is the text element where we are going to show the different songs
    private JTextArea titlePanel;

    public JSong (ArrayList <SongPrueba> songs) {
        //Inicialization of Layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        songsList = new ArrayList<SongView>();
        backButton = new JButton ();
        titlePanel = new JTextArea();

        includeSongs (songs);

        add(backButton);
        add(titlePanel);
    }

    public void makePanelVisible () {
        setVisible(true);
    }

    public void includeSongs (ArrayList <SongPrueba> songs) {
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            songsList.add(new SongView(songs.get(i).getTitle(),songs.get(i).getDescription()));
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,50));
        }
        //Adding all the songs generated to the panel
        addAllTheSongs(songsList);
    }
    private void addAllTheSongs (ArrayList <SongView> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            add(songsViews.get(i));
        }
    }

    public JButton getBackButton() {
        return backButton;
    }
    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public ArrayList<SongView> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<SongView> songsList) {
        this.songsList = songsList;
    }

    public JTextArea getTitlePanel() {
        return titlePanel;
    }

    public void setTitlePanel(JTextArea titlePanel) {
        this.titlePanel = titlePanel;
    }
}
