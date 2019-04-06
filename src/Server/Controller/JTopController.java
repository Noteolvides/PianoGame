package Server.Controller;

import Server.View.JTop;

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
        System.out.println("Clicked");
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
