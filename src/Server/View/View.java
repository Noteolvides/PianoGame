package Server.View;

import Client.View.Images.SongPrueba;

import java.util.ArrayList;

public class View {

    private JGestor gestorView;

    public JGestor getGestorView() {
        return gestorView;
    }

    public void initGestor(ArrayList<SongPrueba> songs) {
        gestorView = new JGestor(songs);
    }
}
