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
    }

    public void closeStart() {
        view.getStartView().setVisible(false);
        view.getStartView().dispose();
    }

    public void openRegister() {
        view.initRegisterView();
        view.getRegisterView().registerController(startController);
    }

    public void closeRegister() {
        view.getRegisterView().setVisible(false);
        view.getRegisterView().dispose();
    }

}
