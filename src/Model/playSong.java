package Model;

import Server.Controller.AudioListener;
import Server.View.JTop;
import Server.View.SongView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class playSong extends Thread {
    //A class to play the songs
    private Clip clip;
    private String songTitle;
    private AudioListener listener;
    private SongView songView;
    private int idSong;

    public playSong(String songTitle, SongView songView, int idSong){
        this.songTitle = songTitle;
        this.setName(songTitle);
        this.songView = songView;
        this.idSong = idSong;
        listener = new AudioListener();
    }

    /*
     * Play the Song Preview when the song icon's clicked on the JTop 5 Panel
     * Multithreading enabled to play multiple songs at the same time
     * Whenever a Song is played its icon gets replaced by a Stop Button
     * If the button is clicked again it stops the song
     */
    public void run(){
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("songTemp/" + songTitle + ".mid").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.addLineListener(listener);
            clip.open(audioInputStream);

            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            try {
                clip.start();
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                listener.waitUntilDone();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            clip.close();
            songView.setPlaying(false);
            songView.resetMusicIcon(idSong);
        }
    }

    //Stops the song from being played
    public void stopClip() {
         clip.stop();
         clip.close();
         this.stop();
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getSongTitle(){
        return songTitle;
    }
}
