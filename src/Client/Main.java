package Client;

import Client.Controller.Controller;

import Client.Network.ClientConnection;
import Client.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.registerController();
        ClientConnection network = new ClientConnection(controller);
        controller.registerNetwork(network);
    }
}
