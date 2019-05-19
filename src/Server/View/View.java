package Server.View;

import Model.Song;

import java.util.List;

/**
 * Server View Class
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
public class View {
    //Every view from the Server
    private Graphic graficView;
    private JEvolution evolutionView;
    private JGestor gestorView;
    private JRegister registerView;
    private JTop topView;
    private SongFile songfile;
    private SongView songView;

    public Graphic getGraficView() {
        return graficView;
    }

    public JEvolution getEvolutionView() {
        return evolutionView;
    }

    public JGestor getGestorView() {
        return gestorView;
    }

    public JRegister getRegisterView() {
        return registerView;
    }

    public JTop getTopView() {
        return topView;
    }

    public SongFile getSongfile() {
        return songfile;
    }

    public SongView getSongView() {
        return songView;
    }

    //Starts the Register View
    public void initRegisterView() {
            registerView = new JRegister();
    }

    //Starts the Song Manager View
    public void initGestorView(List<Song> songs) {
        gestorView = new JGestor(songs);
    }

}
