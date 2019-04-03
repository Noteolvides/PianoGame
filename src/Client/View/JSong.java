package Client.View;

import Client.View.Images.SongPrueba;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.add(new JSong(simulationOfController()));
        test.setSize(400,1000);
        test.setVisible(true);
        test.setResizable(false);
        test.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static ArrayList <SongPrueba> simulationOfController() {
        //This array simulates the return of the controller's call
        ArrayList <SongPrueba> array = new ArrayList<SongPrueba>();
        SongPrueba s = new SongPrueba(1,"Himno Urss","Stallin");
        SongPrueba s1 = new SongPrueba(2,"Melgui:The Song","MelguiUser");
        SongPrueba s2 = new SongPrueba(3,"Yahoo:The Song","Jiahui");
        SongPrueba s3 = new SongPrueba(4,"La Lechuga ta' pocha","Anonymous");
        SongPrueba s4 = new SongPrueba(5,"Los coches chocones","NeilUser");
        SongPrueba s5 = new SongPrueba(6,"Kermitt BSO","Gutavo");
        SongPrueba s6 = new SongPrueba(7,"Las Zanahorias estan rebien","Pepe");
        SongPrueba s7 = new SongPrueba(8,"Javadabadooh","El papu picapiedra");
        SongPrueba s8 = new SongPrueba(9,"Intel y gentes: Musica tite'","Dani y los otros");
        SongPrueba s9 = new SongPrueba(10,"Hola bon dia","El SinMerk3000");
        SongPrueba s10 = new SongPrueba(11,"Bella Ciao Remix","Martin Garrix");
        SongPrueba s11 = new SongPrueba(12,"Urss Anthem Techno Remix","DJ Marx");
        SongPrueba s12 = new SongPrueba(13,"El baile del TCP/IP","TimoTronic");
        SongPrueba s13 = new SongPrueba(14,"TimoTronic","Yahoo");
        SongPrueba s14 = new SongPrueba(15,"Illo eso ka' sido","Pingu");
        array.add(s);
        array.add(s1);
        array.add(s2);
        array.add(s3);
        array.add(s4);
        array.add(s5);
        array.add(s6);
        array.add(s7);
        array.add(s8);
        array.add(s9);
        array.add(s10);
        array.add(s11);
        array.add(s12);
        array.add(s13);
        array.add(s14);
        return array;
    }

    public JSong (ArrayList <SongPrueba> songs) {
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
        ImageIcon backImage = new ImageIcon(getClass().getResource("Images/back.png"));
        //We scale the image because it's too big
        ImageIcon backImage_scaled = new ImageIcon(backImage.getImage().getScaledInstance(backImage.getIconWidth() / 20, backImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
        backButton.setIcon(backImage_scaled);


        ImageIcon refreshImage = new ImageIcon(getClass().getResource("Images/refresh.png"));
        //We scale the image because it's too big
        ImageIcon refreshImage_scaled = new ImageIcon(refreshImage.getImage().getScaledInstance(refreshImage.getIconWidth() / 20, refreshImage.getIconHeight() / 20, Image.SCALE_SMOOTH));
        refreshButton.setIcon(refreshImage_scaled);

        //We define the group of songs as a BoxLayout
        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));


        //Adding the title of the JSong panel
        add(titlePanel);

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



    public ArrayList<SongView> getSongsList() {
        return songsList;
    }

    public void setSongsList(ArrayList<SongView> songsList) {
        this.songsList = songsList;
    }

}
