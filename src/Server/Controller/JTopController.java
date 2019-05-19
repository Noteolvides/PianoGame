package Server.Controller;

import Model.PlaySong;
import Model.Song;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.JTop;
import Server.View.SongView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

/**
 * Controller for the Top 5 View
 * It maps every icon of the panel to its function
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
public class JTopController implements MouseListener {
    private ServiceBBDDServer service;
    private JTop jTop;
    private Set<Thread> setOfThread;
    private ArrayList<PlaySong> music = new ArrayList<>();

    public JTopController(JTop jTop, ServiceBBDDServer service) {
        this.jTop = jTop;
        this.service = service;
    }

    public void mousePressed(MouseEvent event) {

    }

    /**
     * It determines what button is clicked: Play Songs, display the Description
     * @param event: Which button is clicked
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel whichButton = (JLabel) event.getSource();

        //Shows which song is currently playing
        for (int i = 0; i < jTop.getSongsList().size(); i++) {
            if (jTop.getSongsList().get(i).getPlayButton().equals(whichButton)) {
                System.out.println("Playing: " + jTop.getSongsList().get(i).getTitleSong().getText());
            }
        }

        //Determines which song to be played when the its icon is clicked, or paused if it's already playing
        for (int i = 0; i < jTop.getSongsList().size(); i++) {
            if (jTop.getSongsList().get(i).getMusicIcon().equals(whichButton) && !jTop.getSongsList().get(i).isPlaying()) {
                System.out.println(jTop.getSongsList().get(i).getTitleSong().getText() + ":   " + jTop.getSongsList().get(i).getDescription().getText());
                jTop.getSongsList().get(i).pauseMusicIcon();
                String songTitle = jTop.getSongsList().get(i).getTitleSong().getText();

                //TODO: Add Thread to Play Music
                jTop.getSongsList().get(i).setPlaying(true);
                music.add(new PlaySong(songTitle, jTop.getSongsList().get(i), i));
                music.get(music.size()-1).start();

            }else{
                if (jTop.getSongsList().get(i).getMusicIcon().equals(whichButton) && jTop.getSongsList().get(i).isPlaying()){
                    jTop.getSongsList().get(i).setPlaying(false);

                    jTop.getSongsList().get(i).resetMusicIcon(i);
                    for(int j = 0; j < music.size(); j++){
                        System.out.println( music.get(j).getName() + "     " + jTop.getSongsList().get(i).getTitleSong().getText());
                        if(music.get(j).getName().equals(jTop.getSongsList().get(i).getTitleSong().getText())){
                            music.get(j).stopClip();
                        }
                    }
                }
            }
        }

        //Shows each song's description
        for(int i = 0; i < jTop.getSongsList().size(); i++){
            if(jTop.getSongsList().get(i).getInfoIcon().equals(whichButton)){
                System.out.println(jTop.getSongsList().get(i).getDescription().getText());
            }
        }
    }

    /**
     *  Adds and include all the songs retrieved from the data base
     * @param songs : List that contains all the songs
     */
    public void includeSongs (ArrayList <Song> songs) {
        //Adding a new array to not repeat different times the same songs, if we decide to refresh the window
        jTop.setSongsList(new ArrayList<>());
        //We take the songs that we are going to show and we add them to our view
        for (int i = 0;i < songs.size();i++) {
            jTop.getSongsList().add(new SongView(i + 1, songs.get(i).getTitle(), songs.get(i).getDescription()));

            //We put a maximmum size of a song
            jTop.getSongsList().get(i).setMaximumSize(new Dimension(1000,1000));
        }
    }

    /**
     * With all the songs retrieved from the BBDD and further processed
     * It outputs all of them to the Panel's list
     * @param songsViews List of the songs with its correct format, buttons and icons set
     */
    public void addAllTheSongs (ArrayList <SongView> songsViews) {
        for (int i = 0; i < songsViews.size();i++) {
            jTop.getSongsGroup().add(songsViews.get(i));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public ServiceBBDDServer getService() {
        return service;
    }

    public void setService(ServiceBBDDServer service) {
        this.service = service;
    }

    //For further use to refresh the Top 5 Songs if the top changes in the BBDD
    public void refresh(ArrayList<Song> refreshedSongs){
        jTop.getFrameTop().dispose();
        includeSongs(refreshedSongs);
        addAllTheSongs(jTop.getSongsList());
    }
}
