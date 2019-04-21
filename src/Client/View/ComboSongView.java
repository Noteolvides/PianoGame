package Client.View;

import Client.Controller.ControllerJSong;
import javax.swing.*;
import java.util.ArrayList;

public class ComboSongView extends JLayeredPane {
    private JSong jSong;
    private JLabel loading;

    public ComboSongView(JSong jSong, JLabel loading) {
        this.jSong = jSong;
        this.jSong.setBounds(0,0,400,780);
        this.loading = loading;
        ImageIcon loadingImage = new ImageIcon("img/loading.gif");
        //We scale the image because it's too big
        this.loading.setIcon(loadingImage);
        this.loading.setBounds(125,200,250,250);
        add(this.jSong,new Integer(1));

        setVisible(true);
    }

    public void showGif() {
        add(this.loading,new Integer(2));
    }

    public void hideGif () {
        remove(loading);
    }

    public void updateSongs(ArrayList<SongPrueba> arrayList) {
        jSong.includeSongs(arrayList);
        jSong.addAllTheSongs(jSong.getSongsList());
    }
    public void updateControllersSongs (ControllerJSong controllerJSong) {
        jSong.updateControllersSongs(controllerJSong);
    }

    public JSong getjSong() {
        return jSong;
    }
}
