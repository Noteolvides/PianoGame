package Server.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class JTop {
    //public JList songTop;
    private JPanel songTop = new JPanel();
    private ArrayList<SongView> songsList;
    private JPanel songsGroup = new JPanel();

    void JTop(){                            //Pass ArrayList<Song> to the constructor and for each one add Number 1-5

        ArrayList<SongPrueba> songs = new ArrayList<>();
        songs.add(new SongPrueba(1, "Watanabe Mayu", "She's so cute ❤"));
        songs.add(new SongPrueba(2, "Okada Nana", "Elegance and Prestige"));
        songs.add(new SongPrueba(3, "Yabuki Nako", "Mayu's daughter"));
        songs.add(new SongPrueba(4, "Tanaka Miku", "Mayu's daughter"));
        songs.add(new SongPrueba(5, "Yamamoto Sayaka", "Let's rock it!"));


        songTop.setLayout(new BoxLayout(songTop, BoxLayout.Y_AXIS));

        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));


        includeSongs(songs);
        addAllTheSongs(songsList);

        songTop.add(songsGroup);
        //songTop.add()
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
        JFrame frameTop = new JFrame("TOP 5 - Popular Songs"); //Prova
        ImageIcon icon = new ImageIcon("./topicon.png");
        frameTop.setIconImage(icon.getImage());

        frameTop.setSize(390, 450);
        frameTop.setVisible(true);
        frameTop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameTop.add(panelTop);
    }

    public static void main(String[] args) {
        JTop h = new JTop();
        h.JTop();
    }

    public void includeSongs (ArrayList <SongPrueba> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        songsList = new ArrayList<>();
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            songsList.add(new SongView(songs.get(i).getIdSong(), songs.get(i).getTitle(),songs.get(i).getDescription()));
            //We put a maximmum size of a song
            songsList.get(i).setMaximumSize(new Dimension(1000,1000));
        }

    }
    private void addAllTheSongs (ArrayList <SongView> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            songsGroup.add(songsViews.get(i));
        }
    }
    /*void registerController(Controller controller){

    }*/
}
