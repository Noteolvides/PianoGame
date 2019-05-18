package Server.View;

import Model.Song;
import Server.Controller.JTopController;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

@Controller
public class JTop {
    //The main frame of TOP 5 Songs of the BBDD
    private JFrame frameTop = new JFrame("TOP 5 - Popular Songs");
    JPanel panelTop = new JPanel();
    private JPanel songTop = new JPanel();
    /*
     *The structure of the Song List View, icon to the left with name
     *and its description, to the right the play and show description button.
     */
    private ArrayList<SongView> songsList;
    private JPanel songsGroup = new JPanel();
    private ArrayList<Song> songs;
    public JTop(){
    }

    public void JTop(){

        //Adding layouts and all the containing panels
        songTop.setLayout(new BoxLayout(songTop, BoxLayout.Y_AXIS));

        songsGroup.setLayout(new BoxLayout(songsGroup,BoxLayout.Y_AXIS));

        songTop.add(songsGroup);

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

        //Frame global settings
        frameTop.setSize(390, 450);
        frameTop.setResizable(true);
        frameTop.setVisible(false);
        frameTop.add(panelTop);
    }

    /*
    * Register the controller, mapping each button or image with its function
    * playButton - Play Song
    * infoButton - Shows Song Info
    * musicIcon - Plays a demo of the song
    */
    public void registerController(JTopController controller){
        for(int i = 0; i < songsList.size(); i++){
            songsList.get(i).getPlayButton().addMouseListener(controller);
            songsList.get(i).getInfoIcon().addMouseListener(controller);
            songsList.get(i).getMusicIcon().addMouseListener(controller);
        }
    }

    /*
     * Sets the Frame visible if the window is accessed
     */
    public void setVisible(){
        frameTop.setVisible(true);
    }

    public ArrayList<SongView> getSongsList() {
        return songsList;
    }

    public JPanel getSongTop() {
        return songTop;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public JPanel getSongsGroup() {
        return songsGroup;
    }
    public JFrame getFrameTop() {
        return frameTop;
    }
    public JPanel getPanelTop() {
        return panelTop;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setSongsList(ArrayList<SongView> songsList) {
        this.songsList = songsList;
    }
    public void setSongsGroup(JPanel songsGroup) {
        this.songsGroup = songsGroup;
    }

    public void setFrameTop(JFrame frameTop) {
        this.frameTop = frameTop;
    }

    public void setPanelTop(JPanel panelTop) {
        this.panelTop = panelTop;
    }

    public void setSongTop(JPanel songTop) {
        this.songTop = songTop;
    }

    //Further implementation for the refresh of TOP 5 Songs
    public void refreshSongs(int songToRemove){
        if(!songs.isEmpty()){
            songs.remove(songToRemove);
            songsList.remove(songToRemove);
            songTop.repaint();
            songsGroup.repaint();
        }
    }
}
