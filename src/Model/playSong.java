package Model;

import Server.Controller.AudioListener;
import Server.View.JTop;
import Server.View.SongView;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class playSong extends Thread {
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
    public void run(){
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("songTemp/" + songTitle + ".wav").getAbsoluteFile());
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

            /*if (!Thread.currentThread().isAlive()) {
                clip.stop();
            }*/
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
