package Client.View;

import Client.Controller.ControllerJSong;
import Model.Song;

import javax.swing.*;
import java.util.ArrayList;

import static Client.Controller.Controller.*;

public class FinestraJSong extends JFrame{
    private ComboSongView comboSongView;

    public FinestraJSong () {
        comboSongView = new ComboSongView(new JSong(),new JLabel());
        add(comboSongView);
        setSize(400,1000);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    public JSong getjSong() {
        return comboSongView.getjSong();
    }

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

    public void errorPopUp() {
        JOptionPane.showMessageDialog(this, "There was a problem loading the songs.", "Error", JOptionPane.WARNING_MESSAGE);
    }

    public void savePopUp(String petitionResult) {
        if (petitionResult.equals(OK)) {
            JOptionPane.showMessageDialog(this, "The song was saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        if (petitionResult.equals(KO)) {
            JOptionPane.showMessageDialog(this, "There was a problem saving the song.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}
