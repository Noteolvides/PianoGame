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

/**
 * Main Server Class
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class MainServer {
    public static void main(String[] args) throws IOException {
        ApplicationContext context =  new ClassPathXmlApplicationContext("Server/Controller/BBDD/Resources/applicationContextService.xml");
        Server server = (Server)context.getBean("controllerJ2");
        Gson gson = new Gson();
        JsonReader json = null;
        json = new JsonReader(new FileReader("configFiles/config.json"));
        Configuration config = gson.fromJson(json, Configuration.class);
        server.startServer(config.getClientPort());
        ServerViews serverViews = new ServerViews();

        //Inits Server view
        View view = new View();
        view.initRegisterView();
        RegisterController controller = new RegisterController(view,server.getServerService());

        //Inits JEvolution View for a graphical representation of the Users connections to de Database
        JEvolution evolution = new JEvolution(server.getServerService());
        evolution.JEvolution();
        evolution.setEvoVisible(false);
        JEvolutionController evoController = new JEvolutionController(evolution);

        //Inits JTop View for a TOP 5 Songs list of the Database
        JTop top = new JTop();
        top.JTop();
        JTopController topController = new JTopController(top, server.getServerService());
        top.setSongs((ArrayList<Song>) topController.getService().getTop5Songs());
        topController.includeSongs(top.getSongs());
        topController.addAllTheSongs(top.getSongsList());

        //Inits JGestor for managing BBDDD's Song
        GestorController gestorController = new GestorController(view, server.getServerService());
        view.getGestorView().registerController(gestorController);

        //Main controller for the Server View Panel
        Controller control = new Controller(evoController, topController, controller, gestorController, top, evolution, view, serverViews);
        serverViews.registerController(control);
        control.actionManager(evolution, top, view);

    }
}
