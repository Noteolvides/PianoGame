package Client.View;

import Client.Controller.ControllerJSong;
import Model.Song;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;


/**
 * Class that creates the Songs Panel, containing all the songs that the user can access
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class JSong extends JPanel{
    //Basic elements of the JSongs Class
    //This is the View where we are going to show the songs
    private ArrayList<SongView> songsList;
    private JLabel backButton;
    private JLabel refreshButton;
    //This is the panel where we are going to add the back and refresh button
    private JPanel bottomPanel;
    //This is the text element where we are going to show the different songs
    private JLabel titlePanel;

    private JPanel songsGroup;

    private JScrollPane scrollBar;

    /**
     * Constructor, it creates and adds all the jComponents and adds them to a jPanel. (Bassically it have all the initialization of JSocial)
     */
    public JSong () {
        //Inicialization of Layout
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        backButton = new JLabel ();
        refreshButton = new JLabel ();
        bottomPanel = new JPanel ();
        titlePanel = new JLabel();
        songsGroup = new JPanel();

        titlePanel.setText("SONGS LIST ♫");
        titlePanel.setFont(new Font("Alegreya Sans",Font.PLAIN,25));
        //We align the title to the center point
        titlePanel.setAlignmentX(0.5f);
        //Initialitation of the button to go the previous menu
        //Localizing the image in the project
        ImageIcon backImage = new ImageIcon("img/back.png");
        //We scale the image because it's too big
        ImageIcon backImage_scaled = new ImageIcon(backImage.getImage().getScaledInstance(backImage.getIconWidth() / 20, backImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
        backButton.setIcon(backImage_scaled);


        ImageIcon refreshImage = new ImageIcon("img/refresh.png");
        //We scale the image because it's too big
        ImageIcon refreshImage_scaled = new ImageIcon(refreshImage.getImage().getScaledInstance(refreshImage.getIconWidth() / 20, refreshImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
        refreshButton.setIcon(refreshImage_scaled);

        //We define the group of songs as a BoxLayout
        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));


        //Adding the title of the JSong panel
        add(titlePanel);

        songsList = new ArrayList<>();
        //Adding all the songs generated to the panel
        addAllTheSongs(songsList);

        //We make the panel scrolleable
        scrollBar = new JScrollPane(songsGroup);

        add(scrollBar);

        //Definition of the Bottom's panel layout
        bottomPanel.setLayout(new GridLayout());
        backButton.setHorizontalAlignment(JLabel.LEFT);
        bottomPanel.add(backButton);
        //We put an invisible border un the backbutton to separete it form the left margin
        backButton.setBorder(new EmptyBorder(5,5,5,5));

        bottomPanel.add(refreshButton);
        refreshButton.setHorizontalAlignment(JLabel.RIGHT);
        //We put an invisible border un the backbutton to separete it form the right margin
        refreshButton.setBorder(new EmptyBorder(5,5,5,5));
        add(bottomPanel);

    }

    /**
     * This method is the responsible to make the JSong visible
     */
    public void makePanelVisible () {
        setVisible(true);
    }


    /**
     * This method is the responsible to update the information that is going to be shown on the screen
     * @param songs It's the array with the songs that we are going to show on the screen
     */
    public void includeSongs (ArrayList<Song> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        //We take the songs that we are going to show and we add them to our view
        songsList.clear();
        for (int i = 0;i < songs.size();i++) {
            if (songs.get(i).getAuthor() == null) {
                songsList.add(new SongView(songs.get(i).getTitle(),songs.get(i).getDescription(), "System"));
            } else {
                songsList.add(new SongView(songs.get(i).getTitle(), songs.get(i).getDescription(), songs.get(i).getAuthor().getNameUser()));
            }
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,70));
        }

    }

    /**
     * It's the method that update the information of the screen, and clear the old information
     * @param songsViews We pass to this method the different particular views of the songs that we are going to show
     */
    public void addAllTheSongs (ArrayList <SongView> songsViews) {
        //We delete all the songs that we have
        songsGroup.removeAll();
        for (int i = 0; i < songsViews.size();i++) {
            songsGroup.add(songsViews.get(i));
        }
        revalidate();
        repaint();
    }

    /**
     * This is the method to register the controller of the general things (not the concrete things) of the panel (for example, the back button, the refresh button...)
     * @param controllerJSong
     */
    public void registerControllers (ControllerJSong controllerJSong) {
        backButton.addMouseListener(controllerJSong);
        refreshButton.addMouseListener(controllerJSong);
        //The controller of the songs is put when the songs are updated
    }


    /**
     * This method is the responsible to say in which song they have touched the play button
     * @param botoPlay Play button that have been touched
     * @return We return an string with the title of the song touched
     */
    public String searchNameSong(JLabel botoPlay) {
        String title = "";
        int j = 0;
        boolean found = false;
        //We try to discover what song comes from the play touched
        while (j < songsList.size() && !found) {
            if(songsList.get(j).getPlayButton() == botoPlay) {
                found = true;
                title = songsList.get(j).getTitleSong().getText();
            }
            j++;
        }
        return title;
    }

    /**
     * This method is the responsible to say the author of the song that they have touched
     * @param botoPlay Play button that have been touched
     * @return We return an string with the title of the author of the song that have been touched
     */
    public String searchAuthorSong(JLabel botoPlay) {
        String author = "";
        int j = 0;
        boolean found = false;
        //We try to discover what song comes from the play touched
        while (j < songsList.size() && !found) {
            if(songsList.get(j).getPlayButton() == botoPlay) {
                found = true;
                author = songsList.get(j).getAuthor().getText();
            }
            j++;
        }
        return author;
    }

    /**
     * This is the resposible to update the controller of every song that it's on the screen
     * @param controllerJSong We pass the controller that we are going to register in every play button
     */
    public void updateControllersSongs (ControllerJSong controllerJSong) {
        int i = 0;
        //We put our controller in all the songs available
        while (i < songsList.size()) {
            songsList.get(i).registerButtonController(controllerJSong);
            i++;
        }
    }


    public ArrayList<SongView> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<SongView> songsList) {
        this.songsList = songsList;
    }


    public JLabel getBackButton() {
        return backButton;
    }

    public JLabel getRefreshButton() {
        return refreshButton;
    }


}
