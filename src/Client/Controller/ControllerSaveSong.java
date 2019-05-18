package Client.Controller;

import Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSaveSong implements ActionListener {
    private View view;
    private Controller controller;

    public ControllerSaveSong(View view, Controller controller) {
        this.view = view;
        this.controller = controller;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE-SONG")){
            controller.setSongToSave();
            controller.networkSaveSong();
            view.getSaveSongView().getAddSongName().setText("");
            view.getSaveSongView().getSongDescription().setText("");
            view.getSaveSongView().getWannaPrivate().setSelected(false);
        }
    }
}
