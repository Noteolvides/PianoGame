package Client.View;

import Client.Controller.ControllerJSong;
import javax.swing.*;
import java.util.ArrayList;

public class FinestraJSong extends JFrame{
    private ComboSongView comboSongView;

    public FinestraJSong () {
        comboSongView = new ComboSongView(new JSong(),new JLabel());
        add(comboSongView);
        setSize(400,1000);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JSong getjSong() {
        return comboSongView.getjSong();
    }

    public void updateSongs (ArrayList <SongPrueba> arrayList) {
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
}
