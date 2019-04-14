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
        openStart();
        //pianoController = new PianoController(view,this);
        //controllerJSocial = new ControllerJSocial(view);
        //view.getSocialView().registerController(controllerJSocial);
        //view.getFriendsView().registerController(controllerJSocial);
    }

    public void openStart() {
        startController = new StartController(view, this);
        view.getStartView().registerController(startController);
        view.getStartView().setVisible(true);
    }

    public void closeStart() {
        view.getStartView().setVisible(false);
        view.getStartView().dispose();
    }

    public void openRegister() {
        view.initRegisterView();
        view.getRegisterView().registerController(startController);
        view.getRegisterView().setVisible(true);
    }

    public void closeRegister() {
        view.getRegisterView().setVisible(false);
        view.getRegisterView().dispose();
    }

    public void openPrincipal() {
        principalController = new JPrincipalController(view, this);
        view.getPrincipalView().registerController(principalController);
        view.getPrincipalView().setVisible(true);
    }

    public void closePrincipal() {
        view.getPrincipalView().setVisible(false);
        view.getPrincipalView().dispose();
    }

    public void openPiano() {
        pianoController = new PianoController(view,this);
        view.getPianoView().setVisible(true);
    }

    public void closePiano() {
        view.getPianoView().setVisible(false);
        view.getPianoView().dispose();
    }

    public void openSocial() {
        controllerJSocial = new ControllerJSocial(view, this);
        view.getSocialView().registerController(controllerJSocial);
        view.getSocialView().setVisible(true);
    }

    public void closeSocial() {
        view.getSocialView().setVisible(false);
        view.getSocialView().dispose();
    }
}
