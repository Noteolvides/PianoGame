package Server.Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class playSong implements Runnable {
    Clip clip;
    private String songTitle;

    public playSong(String songTitle){
        this.songTitle = songTitle;
    }

    @Override
    public void run() {
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\" + songTitle + ".wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            if(!Thread.currentThread().isAlive()){
                clip.stop();
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopClip() {
         clip.stop();
         clip.close();
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getSongTitle(){
        return songTitle;
    }
}
