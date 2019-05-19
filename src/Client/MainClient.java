package Client;

import Client.Controller.Controller;

import Client.Network.ClientConnection;
import Client.View.View;
import Model.ConfigurationPackage.Configuration;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Main Client Class
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class MainClient {
    public static void main(String[] args) throws FileNotFoundException {
        View view = new View();
        Controller controller = new Controller(view);
        controller.registerController();
        //Config Read
        Gson gson = new Gson();
        JsonReader json = null;
        json = new JsonReader(new FileReader("configFiles/config.json"));
        Configuration config = gson.fromJson(json, Configuration.class);
        //Config read enn
        ClientConnection network = new ClientConnection(controller,config.getClientPort());
        controller.registerNetwork(network);
        network.run();
    }
}
