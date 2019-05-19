package Client.View;

import Client.Controller.ControllerJSong;
import Model.Song;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This is the class that combines all the songs with the loading bar
 * I mean it's the kind that lets you put all the songs with a little loading bar in front of it
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class ComboSongView extends JLayeredPane {
    private JSong jSong;
    private JLabel loading;

    /**
     * This method initialize the window with all the songs avaiable
     * @param jSong This is the panel that contains all the songs
     * @param loading This is the loading bar that where we can choose a concrete song
     */
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

    /**
     * Method to print the loading bar in the window
     */
    public void showGif() {
        add(this.loading,new Integer(2));
    }

    /**
     * Method to hide the loading bar in the window
     */
    public void hideGif () {
        remove(loading);
        repaint();
        revalidate();
    }

    /**
     *
     * @param arrayList
     */
    public void updateSongs(ArrayList<Song> arrayList) {
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
