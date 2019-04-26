package Server.Controller;

import Server.View.JTop;
import Server.View.SongPrueba;
import Server.View.SongView;
import javax.media.Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public class JTopController implements MouseListener {
    private JTop jTop;
    private Set<Thread> setOfThread;

    public JTopController(JTop jTop) {
        this.jTop = jTop;
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

                //TODO: Add Thread to Play Music
                jTop.getSongsList().get(i).setPlaying(true);
                Runnable music = new Runnable() {
                    @Override
                    public void run() {
                        AudioInputStream audioInputStream;
                        Clip clip;
                        try {
                            audioInputStream = AudioSystem.getAudioInputStream(new File("C:\\Users\\Jiahu\\Music\\La vie en Rose IZONE.wav").getAbsoluteFile());
                            clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
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
                };
                Thread playMusic = new Thread(music, jTop.getSongsList().get(i).getTitleSong().getText());
                playMusic.start();
                setOfThread = Thread.getAllStackTraces().keySet();
                System.out.println("Thread created: " + playMusic.getName() +  "   " + playMusic.isAlive());
            }else{
                if (jTop.getSongsList().get(i).getMusicIcon().equals(whichButton) && jTop.getSongsList().get(i).isPlaying()){
                    jTop.getSongsList().get(i).setPlaying(false);

                    jTop.getSongsList().get(i).resetMusicIcon(i);

                    //Iterate over set to find yours
                    for(Thread thread : setOfThread){
                        if(thread.getName().equals(jTop.getSongsList().get(i).getTitleSong().getText())){
                            thread.stop();
                            System.out.println("Thread stopped: " + thread + thread.isAlive());
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

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
