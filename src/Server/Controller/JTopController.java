package Server.Controller;

import Server.View.JTop;
import Server.View.SongPrueba;
import Server.View.SongView;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class JTopController implements MouseListener {
    private JTop jTop;


    public JTopController(JTop jTop) {
        this.jTop = jTop;
    }

    public void mousePressed(MouseEvent event){

    }

    @Override
    public void mouseClicked(MouseEvent event){
        JLabel whichButton = (JLabel) event.getSource();

        for(int i = 0; i < jTop.getSongsList().size(); i++){
            if(jTop.getSongsList().get(i).getPlayButton().equals(whichButton)){
                System.out.println("Playing: " + jTop.getSongsList().get(i).getTitleSong().getText());
            }
        }
        for(int i = 0; i < jTop.getSongsList().size(); i++){
            if(jTop.getSongsList().get(i).getMusicIcon().equals(whichButton)){
                System.out.println(jTop.getSongsList().get(i).getTitleSong().getText() + ":   " + jTop.getSongsList().get(i).getDescription().getText());
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
