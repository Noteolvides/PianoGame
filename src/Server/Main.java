package Server;

import Server.Controller.RegisterController;
import Server.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view);
        view.getRegisterView().registerController(controller);
    }
}
