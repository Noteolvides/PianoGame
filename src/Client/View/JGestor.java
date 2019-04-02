package Client.View;

import Client.View.Images.SongPrueba;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class JGestor extends JFrame {
        //Basic elements of the JSongs Class
        //This is the View where we are going to show the songs
        private ArrayList<SongView> songsList;
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


    public JGestor (ArrayList<SongPrueba> songs){
            //Inicialization of Layout
            setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

            //Inicialitation of the visual elements
            backButton = new JLabel ();
            refreshButton = new JLabel ();
            addButton = new JLabel();
            topPanel = new JPanel();
            bottomPanel = new JPanel ();
            titlePanel = new JLabel();
            songsGroup = new JPanel();

            titlePanel.setText("SONGS LIST ♫");
            titlePanel.setFont(new Font("Alegreya Sans", Font.PLAIN,25));
            //We align the title to the center point
            titlePanel.setAlignmentX(0.5f);
            //Initialitation of the button to go the previous menu
            //Localizing the image in the project
            ImageIcon backImage = new ImageIcon(getClass().getResource("Images/back.png"));
            //We scale the image because it's too big
            ImageIcon backImage_scaled = new ImageIcon(backImage.getImage().getScaledInstance(backImage.getIconWidth() / 20, backImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
            backButton.setIcon(backImage_scaled);

            ImageIcon refreshImage = new ImageIcon(getClass().getResource("Images/refresh.png"));
            //We scale the image because it's too big
            ImageIcon refreshImage_scaled = new ImageIcon(refreshImage.getImage().getScaledInstance(refreshImage.getIconWidth() / 20, refreshImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
            refreshButton.setIcon(refreshImage_scaled);

            ImageIcon addImage = new ImageIcon(getClass().getResource("Images/add_button.png"));
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

    }

    public void makePanelVisible() {
        setVisible(true);
    }

    public void includeSongs(ArrayList <SongPrueba> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        songsList = new ArrayList<>();
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            songsList.add(new SongView(songs.get(i).getTitle(),songs.get(i).getDescription(), songs.get(i).getPrivacity()));
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,70));
        }

    }

    private void addAllTheSongs (ArrayList <SongView> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            songsGroup.add(songsViews.get(i));
        }
    }

    public ArrayList<SongView> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<SongView> songsList) {
        this.songsList = songsList;
    }


}

