package Server.Controller;

import Model.Song;
import Model.playSong;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.JTop;
import Server.View.SongView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

public class JTopController implements MouseListener {
    private ServiceBBDDServer service;
    private JTop jTop;
    private Set<Thread> setOfThread;
    private ArrayList<playSong> music = new ArrayList<>();

    public JTopController(JTop jTop, ServiceBBDDServer service) {
        this.jTop = jTop;
        this.service = service;
    }

    public void mousePressed(MouseEvent event) {

    }

    @Override
    public void mouseClicked(MouseEvent event) {
        JLabel whichButton = (JLabel) event.getSource();

        for (int i = 0; i < jTop.getSongsList().size(); i++) {
            if (jTop.getSongsList().get(i).getPlayButton().equals(whichButton)) {
                System.out.println("Playing: " + jTop.getSongsList().get(i).getTitleSong().getText());
            }
        }

        for (int i = 0; i < jTop.getSongsList().size(); i++) {
            if (jTop.getSongsList().get(i).getMusicIcon().equals(whichButton) && !jTop.getSongsList().get(i).isPlaying()) {
                System.out.println(jTop.getSongsList().get(i).getTitleSong().getText() + ":   " + jTop.getSongsList().get(i).getDescription().getText());
                jTop.getSongsList().get(i).pauseMusicIcon();
                String songTitle = jTop.getSongsList().get(i).getTitleSong().getText();

                //TODO: Add Thread to Play Music
                jTop.getSongsList().get(i).setPlaying(true);
                music.add(new playSong(songTitle, jTop.getSongsList().get(i), i));
                //Thread playMusic = new Thread(music, jTop.getSongsList().get(i).getTitleSong().getText());
                //playMusic.start();
                music.get(music.size()-1).start();

                //System.out.println("Thread created: " + playMusic.getName() +  "   " + playMusic.isAlive());
                //setOfThread = Thread.getAllStackTraces().keySet();
                //System.out.println("Thread created: " + music.getName() +  "   " + music.isAlive());
            }else{
                if (jTop.getSongsList().get(i).getMusicIcon().equals(whichButton) && jTop.getSongsList().get(i).isPlaying()){
                    jTop.getSongsList().get(i).setPlaying(false);

                    jTop.getSongsList().get(i).resetMusicIcon(i);

                    //Iterate over set to find yours
                    /*for(Thread thread : setOfThread){
                        if(thread.getName().equals(jTop.getSongsList().get(i).getTitleSong().getText())){
                            //System.out.println(music.getSongTitle());
                            music.get(music.size()-1).stopClip();
                            //thread.stop();
                            System.out.println("Thread stopped: " + thread + thread.isAlive());
                        }
                    }*/
                    for(int j = 0; j < music.size(); j++){
                        System.out.println( music.get(j).getName() + "     " + jTop.getSongsList().get(i).getTitleSong().getText());
                        if(music.get(j).getName().equals(jTop.getSongsList().get(i).getTitleSong().getText())){
                            music.get(j).stopClip();
                        }
                    }
                }
            }
        }


        for(int i = 0; i < jTop.getSongsList().size(); i++){
            if(jTop.getSongsList().get(i).getInfoIcon().equals(whichButton)){
                System.out.println(jTop.getSongsList().get(i).getDescription().getText());
            }
        }
    }

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
}
