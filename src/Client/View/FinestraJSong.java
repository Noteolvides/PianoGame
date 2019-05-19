package Client.View;

import Client.Controller.ControllerJSong;
import Model.Song;

import javax.swing.*;
import java.util.ArrayList;

import static Client.Network.ClientConnection.*;

/**
 * Class that creates the Frame of the Song view.
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class FinestraJSong extends JFrame{
    private ComboSongView comboSongView;

    /**
     * Method that initialize the song view with some specific charcateristics
     */
    public FinestraJSong () {
        comboSongView = new ComboSongView(new JSong(),new JLabel());
        add(comboSongView);
        setSize(400,800);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public JSong getjSong() {
        return comboSongView.getjSong();
    }


    /**
     * Method to update all the songs that we can see on the screen
     * @param arrayList Arraylist that contains all the information of all the songs that we are going to show on the screen
     */
    public void updateSongs (ArrayList<Song> arrayList) {
        comboSongView.updateSongs(arrayList);
    }
    public void updateControllersSongs (ControllerJSong controllerJSong) {
        comboSongView.updateControllersSongs(controllerJSong);
    }

    public void hideLoading () {
        comboSongView.hideGif();
    }

    public void showLoading () {
        comboSongView.showGif();
    }

    /**
     * Function that shows a PopUp with the information of the adding a friend.
     * @param petitionResult Type of error.
     */
    public void errorPopUp(int petitionResult) {
        if (petitionResult == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "There was a problem with the database.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "There was a problem loading the songs.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Function that shows a PopUp with the information of the adding a friend.
     * @param petitionResult Type of error.
     */
    public void requestPopUp (int petitionResult) {
        if (petitionResult == OK) {
            JOptionPane.showMessageDialog(this, "The selected song was successfully loaded.", "Song request", JOptionPane.INFORMATION_MESSAGE);
        }
        if (petitionResult == KO) {
            JOptionPane.showMessageDialog(this, "Couldn't connect to the server.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_BBDD) {
            JOptionPane.showMessageDialog(this, "There was a problem with the database.", "Error", JOptionPane.WARNING_MESSAGE);
        }
        if (petitionResult == ERROR_OBJECT) {
            JOptionPane.showMessageDialog(this, "There was a problem with the song.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
