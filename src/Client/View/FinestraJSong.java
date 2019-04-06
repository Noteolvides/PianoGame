package Client.View;

import Client.Controller.ControllerJSong;
import Client.View.Images.SongPrueba;

import javax.swing.*;
import java.util.ArrayList;

public class FinestraJSong extends JFrame{
    private JSong jSong;
    public FinestraJSong () {
        jSong = new JSong();
        add(jSong);
        setSize(400,1000);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JSong getjSong() {
        return jSong;
    }

    public static void main(String[] args) {
        FinestraJSong finestraJSong = new FinestraJSong();
        ControllerJSong controllerJSong = new ControllerJSong(finestraJSong);
        finestraJSong.getjSong().registerControllers(controllerJSong);
    }
    public void updateSongs (ArrayList <SongPrueba> arrayList) {
        jSong.includeSongs(arrayList);
        jSong.addAllTheSongs(jSong.getSongsList());
    }
}