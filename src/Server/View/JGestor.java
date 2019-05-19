package Server.View;

import Model.Song;
import Server.Controller.Gestor.GestorController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager Songs View
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
public class JGestor extends JFrame {
    //Basic elements of the JSongs Class
    //This is the ViewServer where we are going to show the songs
    private ArrayList<SongFile> songsList;
    private JLabel backButton;
    private JLabel refreshButton;
    private JLabel addButton;
    //This is the panel where we are going to add the back and refresh button
    private JPanel bottomPanel;
    //This is the text element where we are going to show the different songs
    private JLabel titlePanel;
    private JPanel topPanel;
    private JPanel songsGroup;
    private JScrollPane scrollBar;

    /**
     * Song Manager View, it offers a friendly UI for the user to delete or add
     * songs to the Server's Database
     * @param songs: List of the songs that the view will contain
     */
    public JGestor (List<Song> songs){
        //Inicialization of Layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        //Inicialitation of the visual elements
        backButton = new JLabel();
        refreshButton = new JLabel();
        addButton = new JLabel();

        topPanel = new JPanel();
        bottomPanel = new JPanel ();
        titlePanel = new JLabel();
        songsGroup = new JPanel();

        titlePanel.setText("SONGS MANAGER ♫");
        titlePanel.setFont(new Font("Alegreya Sans", Font.PLAIN,25));
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

        ImageIcon addImage = new ImageIcon("img/add_button.png");
        ImageIcon addImage_scaled = new ImageIcon(addImage.getImage().getScaledInstance(addImage.getIconWidth() / 20, refreshImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
        addButton.setIcon(addImage_scaled);

        //We define the group of songs as a BoxLayout
        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));

        //Adding the title of the JSong panel
        topPanel.setLayout(new BorderLayout());
        titlePanel.setBorder(new EmptyBorder(0,80,0,0));
        topPanel.add(titlePanel, BorderLayout.CENTER);
        addButton.setBorder(new EmptyBorder(5,0,5,5));
        topPanel.add(addButton, BorderLayout.LINE_END);
        add(topPanel);

        includeSongs (songs);
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

        //Default setting to the view
        setSize(400,600);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /**
     * Registers the Song Manager Controller to the view
     * @param gc: Controller
     */
    public void registerController(GestorController gc){
        backButton.addMouseListener(gc);
        addButton.addMouseListener(gc);
        refreshButton.addMouseListener(gc);

        for (int i = 0; i < songsList.size(); i++) {
            songsList.get(i).registerController(gc);
        }
    }

    /**
     *  Adds and include all the songs retrieved from the data base
     * @param songs : List that contains all the songs
     */
    public void includeSongs(List <Song> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        songsList = new ArrayList<>();
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getAuthor() != null){
                songsList.add(new SongFile(songs.get(i).getTitle(), songs.get(i).getDescription(), songs.get(i).getAuthor().getNameUser(), songs.get(i).getPrivacity()));
            } else {
                songsList.add(new SongFile(songs.get(i).getTitle(), songs.get(i).getDescription(), songs.get(i).getSystem().getName(), songs.get(i).getPrivacity()));
            }

            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,70));
        }

    }

    /**
     * With all the songs retrieved from the BBDD and further processed
     * It outputs all of them to the Panel's list
     * @param songsViews List of the songs with its correct format, buttons and icons set
     */
    private void addAllTheSongs (List <SongFile> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            songsGroup.add(songsViews.get(i));
        }
    }

    public ArrayList<SongFile> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<SongFile> songsList) {
        this.songsList = songsList;
    }

    public JLabel getBackButton() {
        return backButton;
    }

    public JLabel getRefreshButton() {
        return refreshButton;
    }

    public JLabel getAddButton() {
        return addButton;
    }

    public void setVisible(){
        setVisible(true);
    }

    public void cantSaveMsg() {
        JOptionPane.showMessageDialog(this, "Error saving the song into the server.\n" +
                "Song can't contain spaces in the name", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void cantDeleteMsg() {
        JOptionPane.showMessageDialog(this, "This system song can not be deleted", "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public void deletedMsg() {
        JOptionPane.showMessageDialog(this, "Song deleted", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}

