package Server;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.Controller.Controller;
import Server.Controller.JEvolutionController;
import Server.Controller.JTopController;
import Server.Controller.RegisterController;
import Server.Network.Server;
import Server.View.JEvolution;
import Server.View.JTop;
import Server.View.ServerViews;
import Server.View.View;
//import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.startServer();
        ServerViews serverViews = new ServerViews();

        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view);
        //view.getRegisterView().registerController(controller);

        JEvolution evolution = new JEvolution();
        evolution.JEvolution();
        JEvolutionController evoController = new JEvolutionController(evolution);

        JTop top = new JTop(server.getServerService());
        top.JTop();
        JTopController topController = new JTopController(top);

        Controller control = new Controller(evoController, topController, controller, top, evolution, view, serverViews);
        serverViews.registerController(control);
        control.actionManager(evolution, top, view);
    }
}
