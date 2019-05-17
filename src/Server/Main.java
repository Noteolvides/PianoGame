package Server;

import Model.ConfigurationPackage.Configuration;
import Model.Song;
import Model.User;
import Server.Controller.Controller;
import Server.Controller.Gestor.GestorController;
import Server.Controller.JEvolutionController;
import Server.Controller.JTopController;
import Server.Controller.RegisterController;
import Server.Network.Server;
import Server.View.*;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext context =  new ClassPathXmlApplicationContext("Server/Controller/BBDD/Resources/applicationContextService.xml");
        Server server = (Server)context.getBean("controllerJ2");
        Gson gson = new Gson();
        JsonReader json = null;
        json = new JsonReader(new FileReader("configFiles/config.json"));
        Configuration config = gson.fromJson(json, Configuration.class);
        server.startServer(config.getClientPort());
        ServerViews serverViews = new ServerViews();


        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view,server.getServerService());

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

        GestorController gestorController = new GestorController(view, server.getServerService());
        view.getGestorView().registerController(gestorController);

        Controller control = new Controller(evoController, topController, controller, gestorController, top, evolution, view, serverViews);
        serverViews.registerController(control);
        control.actionManager(evolution, top, view);

        //TODO: Delete this
    }
}
