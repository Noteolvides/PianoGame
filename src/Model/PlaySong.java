package Model;

import Server.Controller.AudioListener;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.View.JTop;
import Server.View.SongView;
import org.jfugue.realtime.RealtimePlayer;
import org.jfugue.pattern.*;

import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class that permits a song to be played.
 * It used threads to handle all the songs
 * that is currently playing
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
public class PlaySong extends Thread {
    //A class to play the songs
    private Clip clip;
    private String songTitle;
    private AudioListener listener;
    private SongView songView;
    private int idSong;
    private RealtimePlayer player;
    private String path;

    /**
     * Constructor, it creates and adds all the jComponents and adds them to a jPanel. (Bassically it have all the initialization of PlaySong class)
     * @param songTitle The song title that it's going to be shown
     * @param songView The view that contains all the information of the song
     * @param idSong The id of the song that we are referenciating
     */
    public PlaySong(String songTitle, SongView songView, int idSong, String path){
        this.songTitle = songTitle;
        this.setName(songTitle);
        this.songView = songView;
        this.idSong = idSong;
        this.path = path;
        listener = new AudioListener();
    }

    /**
     * Play the Song Preview when the song icon's clicked on the JTop 5 Panel
     * Multithreading enabled to play multiple songs at the same time
     * Whenever a Song is played its icon gets replaced by a Stop Button
     * If the button is clicked again it stops the song
     */
    public void run(){
        AudioInputStream audioInputStream;
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Pattern p = new Pattern(br.readLine());
            player = new RealtimePlayer();


            /*audioInputStream = AudioSystem.getAudioInputStream(new File("FilesBBDD/Public/System/" + songTitle + ".mid").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);*/

            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            try {
                player.play(p.toString());
                //clip.start();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listener.waitUntilDone();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            player.close();
            songView.setPlaying(false);
            songView.resetMusicIcon(idSong);
        }
    }



    /**
     * Stops the song from being played
     */
    public void stopClip() {
        player.close();
         /*clip.stop();
         clip.close();
         this.stop();*/
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getSongTitle(){
        return songTitle;
    }
}
