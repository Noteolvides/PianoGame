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

    private JPanel songsGroup;

    private JScrollPane scrollBar;

    public JSong (ArrayList <SongPrueba> songs) {
        //Inicialization of Layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        backButton = new JButton ();
        titlePanel = new JTextArea();
        songsGroup = new JPanel();


        //We define the group of songs as a BoxLayout
        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));


        includeSongs (songs);
        //Adding all the songs generated to the panel
        addAllTheSongs(songsList);

        //We make the panel scrolleable
        scrollBar = new JScrollPane(songsGroup);

        add(scrollBar);
        add(backButton);
        add(titlePanel);
    }

    public void makePanelVisible () {
        setVisible(true);
    }

    public void includeSongs (ArrayList <SongPrueba> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        songsList = new ArrayList<>();
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            songsList.add(new SongView(songs.get(i).getTitle(),songs.get(i).getDescription()));
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,70));
        }

    }
    private void addAllTheSongs (ArrayList <SongView> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            songsGroup.add(songsViews.get(i));
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
