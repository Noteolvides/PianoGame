package View;

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

    public JSong () {
        //Inicialization of Layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        songsList = new ArrayList<SongView>();
        backButton = new JButton ();
        titlePanel = new JTextArea();

        songsList.add(new SongView());

        //We put all this elements in the JPanel
        //We include all the songs to the panel
        for (int i = 0; i < songsList.size();i++) {
            add(songsList.get(i));
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,50));
        }
        add(backButton);
        add(titlePanel);
    }

    public void makePanelVisible () {
        setVisible(true);
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
