package Server;

import Server.Controller.Controller;
import Server.Controller.JEvolutionController;
import Server.Controller.JTopController;
import Server.Controller.RegisterController;
import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view);
        //view.getRegisterView().registerController(controller);

        JEvolution evolution = new JEvolution();
        evolution.JEvolution();
        JEvolutionController evoController = new JEvolutionController(evolution);
        JTop top = new JTop();
        top.JTop();
        JTopController topController = new JTopController(top);

        Controller control = new Controller(evoController, topController, controller);
        control.actionManager(evolution, top, view);
    }
}
