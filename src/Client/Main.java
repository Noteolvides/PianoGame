package Client;

import Client.Controller.Controller;

import Client.View.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.registerController();
    }
}
