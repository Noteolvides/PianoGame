package Client;

import Client.Controller.Start.StartController;
import Client.View.Start.JStart;
import Client.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        view.initStartView();
        StartController controller = new StartController(view);
        view.getStartView().registerController(controller);
    }
}
