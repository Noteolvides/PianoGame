package Client.View;

import Client.Controller.ControllerJSong;
import Client.View.Images.SongPrueba;

import javax.swing.*;
import java.util.ArrayList;

public class FinestraJSong extends JFrame{
    private ComboSongView comboSongView;
    public FinestraJSong () {
        comboSongView = new ComboSongView(new JSong(),new JLabel());
        add(comboSongView);
        setSize(400,1000);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JSong getjSong() {
        return comboSongView.getjSong();
    }

    public static void main(String[] args) {
        FinestraJSong finestraJSong = new FinestraJSong();
        ControllerJSong controllerJSong = new ControllerJSong(finestraJSong);
        finestraJSong.getjSong().registerControllers(controllerJSong);
    }
    public void updateSongs (ArrayList <SongPrueba> arrayList) {
        comboSongView.updateSongs(arrayList);
    }
    public void updatePlayControllers (ControllerJSong controllerJSong) {
        comboSongView.updatePlayControllers(controllerJSong);
    }
    public void hideLoading () {
        comboSongView.hideGif();
    }
    public void showLoading () {
        comboSongView.showGif();
    }
}
