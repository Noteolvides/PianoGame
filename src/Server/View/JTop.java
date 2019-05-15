package Server.View;

import Model.Song;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.Controller.JTopController;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

@Controller
public class JTop {
    private ServiceBBDDServer service;
    private JFrame frameTop = new JFrame("TOP 5 - Popular Songs");
    private JPanel songTop = new JPanel();
    private ArrayList<SongView> songsList;
    private JPanel songsGroup = new JPanel();

    public JTop(ServiceBBDDServer service){
        this.service = service;
    }

    public void JTop(){                            //Pass ArrayList<Song> to the constructor and for each one add Number 1-5
        ArrayList<Song> songs;
        songs = (ArrayList<Song>) service.getTop5Songs();

        songTop.setLayout(new BoxLayout(songTop, BoxLayout.Y_AXIS));

        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));


        includeSongs(songs);
        addAllTheSongs(songsList);

        songTop.add(songsGroup);

        JPanel panelTop = new JPanel();
        panelTop.add(songTop);



        //Compound border for Visual Effects
        Border compound, raisedbevel, loweredbevel, redLine;
        raisedbevel = BorderFactory.createRaisedBevelBorder();
        loweredbevel = BorderFactory.createLoweredBevelBorder();
        redLine = BorderFactory.createLineBorder(Color.YELLOW);

        //Adds up both raised and lowered
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);

        //Adds the outer yellow line
        compound = BorderFactory.createCompoundBorder(redLine, compound);

        compound = BorderFactory.createTitledBorder(compound, "\uD83D\uDC51 TOP 5 - Popular Songs \uD83D\uDC51", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        panelTop.setBorder(compound);

        ((TitledBorder) compound).setTitleFont(((TitledBorder) compound).getTitleFont().deriveFont(25.00f));
        ImageIcon icon = new ImageIcon("img/topicon.png");
        frameTop.setIconImage(icon.getImage());

        frameTop.setSize(390, 450);
        frameTop.setResizable(false);
        frameTop.setVisible(false);
        //frameTop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTop.add(panelTop);
    }

    //TODO: ESTO NO SERIA PARTE DEL CONTROLLER?????
    public void includeSongs (ArrayList <Song> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        songsList = new ArrayList<>();
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            songsList.add(new SongView(i + 1, songs.get(i).getTitle(), songs.get(i).getDescription()));

            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,1000));
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

    public void registerController(JTopController controller){
        for(int i = 0; i < songsList.size(); i++){
            songsList.get(i).getPlayButton().addMouseListener(controller);
            songsList.get(i).getInfoIcon().addMouseListener(controller);
            songsList.get(i).getMusicIcon().addMouseListener(controller);
        }
    }

    public JPanel getSongTop() {
        return songTop;
    }
    public void setVisible(){
        frameTop.setVisible(true);
    }

    public void setInvisible(){
        frameTop.setVisible(false);
    }

}
