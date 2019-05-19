package Client.Controller;

import Client.View.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class for the SaveSong view
 * that implements ActionListener.
 */
public class ControllerSaveSong implements ActionListener {
    private View view;
    private Controller controller;

    /**
     * SaveSong Controller constructor, it assigns the view and the father controller
     * @param view View Mother of all views
     * @param controller Main Controller
     */
    public ControllerSaveSong(View view, Controller controller) {
        this.view = view;
        this.controller = controller;

    }

    /**
     * Method implemented from the ActionListener.
     * @param e Action done in the View.
     */
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
