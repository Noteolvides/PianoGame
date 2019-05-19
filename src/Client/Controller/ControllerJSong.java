package Client.Controller;

import Client.View.View;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Controller class for the Song View that implements MouseListener.
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
public class ControllerJSong implements MouseListener {
    private View view;
    private Controller controller;
    private LoadingThread loadingThread;
    private String actualSong;
    private String actualAuthor;

    /**
     * Constructor that assigns the view and the father controller
     * @param view Father view.
     * @param controller Father controller.
     */
    public ControllerJSong (View view, Controller controller) {
        this.view = view;
        this.controller = controller;
        view.getSongView().updateControllersSongs(this);
        loadingThread = new LoadingThread(view.getSongView(),this);
    }

    /**
     * Implemented function, action to do when mouse is clicked.
     * @param e Mouse event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Implemented function, action to do when mouse is pressed.
     * @param e Mouse event.
     */
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

    /**
     * Implemented function, action to do when mouse is released.
     * @param e Mouse event.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Implemented function, action to do when mouse is entered.
     * @param e Mouse event.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Implemented function, action to do when mouse is exited.
     * @param e Mouse event.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
