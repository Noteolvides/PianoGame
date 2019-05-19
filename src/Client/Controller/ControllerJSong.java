package Client.Controller;

import Client.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerJSong implements MouseListener {
    private View view;
    private Controller controller;
    //TODO: I'm not sure that this variable can be here
    private LoadingThread loadingThread;
    private String actualSong;
    private String actualAuthor;
    public ControllerJSong (View view, Controller controller) {
        this.view = view;
        this.controller = controller;
        view.getSongView().updateControllersSongs(this);
        loadingThread = new LoadingThread(view.getSongView(),this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == view.getSongView().getjSong().getBackButton()) {
            controller.closeSong();
            controller.openPiano();
        }
        else {
            if(e.getSource() == view.getSongView().getjSong().getRefreshButton()) {
                loadingThread.start();
                //Then, we re-create the thread to call it the next time (you can't call the thread again)
                loadingThread = new LoadingThread(view.getSongView(),this);
            }
            else {
                //We search the name of the song and then we print playing with it
                actualSong = view.getSongView().getjSong().searchNameSong((JLabel)e.getSource());
                actualAuthor = view.getSongView().getjSong().searchAuthorSong((JLabel)e.getSource());
                //TODO:En piano se podria crear una string y entonces, pasarle el nombre de la cancion (No esta hecho porque pienso que es mejor hacerlo despues del merge)
                controller.networkRequestSong();
                controller.openPiano();
                controller.closeSong();
            }
        }
    }

    public String getActualSong() {
        return actualSong;
    }

    public String getActualAuthor() {
        return actualAuthor;
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
