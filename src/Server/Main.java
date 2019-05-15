package Server;

import Model.Song;
import Model.User;
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

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context =  new ClassPathXmlApplicationContext("Server/Controller/BBDD/Resources/applicationContextService.xml");
        Server server = (Server)context.getBean("controllerJ2");
        server.startServer();
        ServerViews serverViews = new ServerViews();
        /*try {
            server.getServerService().insertSongFromUser("pepe", 69, "marcviolalolis", server.getServerService().getInstanceOfAUser("josep", "roig"), 2, "mayu.mp3");
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view,server.getServerService());
        //view.getRegisterView().registerController(controller);

        JEvolution evolution = new JEvolution(server.getServerService());
        evolution.JEvolution();
        evolution.setEvoVisible(false);
        JEvolutionController evoController = new JEvolutionController(evolution);

        JTop top = new JTop();
        top.JTop();
        JTopController topController = new JTopController(top, server.getServerService());
        top.setSongs((ArrayList<Song>) topController.getService().getTop5Songs());
        topController.includeSongs(top.getSongs());
        topController.addAllTheSongs(top.getSongsList());

        Controller control = new Controller(evoController, topController, controller, top, evolution, view, serverViews);
        serverViews.registerController(control);
        control.actionManager(evolution, top, view);
    }
}
