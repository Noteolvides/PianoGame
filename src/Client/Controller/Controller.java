package Client.Controller;

import Client.Controller.MenuPrincipal.JPrincipalController;
import Client.Controller.Piano.PianoController;
import Client.Controller.Start.StartController;
import Client.View.View;

public class Controller {
    private View view;
    private StartController startController;
    private JPrincipalController principalController;
    private PianoController pianoController;
    private ControllerJSocial controllerJSocial;
    private ControllerJSong controllerJSong;

    public Controller(View view) {
        this.view = view;
        view.initStartView();
    }

    public void registerController() {
        startController = new StartController(view, this);
        view.getStartView().registerController(startController);

        view.initRegisterView();
        view.getRegisterView().registerController(startController);
        closeRegister();

        view.initPrincipalView();
        principalController = new JPrincipalController(view, this);
        view.getPrincipalView().registerController(principalController);
        closePrincipal();

        view.initSocialView();
        controllerJSocial = new ControllerJSocial(view,this);
        view.getSocialView().registerController(controllerJSocial);
        closeSocial();

        view.initSongView();
        controllerJSong = new ControllerJSong(view, this);
        view.getSongView().getjSong().registerControllers(controllerJSong);
        closeSong();

        pianoController = new PianoController(view,this);
        closePiano();

    }

    public void openStart() {
        view.getStartView().setVisible(true);
    }

    public void closeStart() {
        view.getStartView().setVisible(false);
        view.getStartView().dispose();
    }

    public void openRegister() {
        view.getRegisterView().setVisible(true);
    }

    public void closeRegister() {
        view.getRegisterView().setVisible(false);
        view.getRegisterView().dispose();
    }

    public void openPrincipal() {
        view.getPrincipalView().setVisible(true);
    }

    public void closePrincipal() {
        view.getPrincipalView().setVisible(false);
        view.getPrincipalView().dispose();
    }

    public void openPiano() {
        view.getPianoView().setVisible(true);
    }

    public void closePiano() {
        view.getPianoView().setVisible(false);
        view.getPianoView().dispose();
    }

    public void openSocial() {
        view.getSocialView().setVisible(true);
    }

    public void closeSocial() {
        view.getSocialView().setVisible(false);
        view.getSocialView().dispose();
    }

    public void openSong() {
        view.getSongView().setVisible(true);
    }

    public void closeSong() {
        view.getSongView().setVisible(false);
        view.getSongView().dispose();
    }
}
