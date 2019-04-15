package Client.Controller.Network;

import Client.Controller.Controller;

import java.io.*;
import java.net.Socket;

public class ClientConnection {
    //Connection const.
    private static final int PORT = 5000;
    private static final String IP = "localhost";

    private static final int RECEIVED = 0;
    private static final int ERROR = -1;

    private static final int LOGIN = 1;
    private static final float SEND_LOG_USER = 1.1f;
    private static final float GO_BACK = -1.1f;

    private static final int REGISTER = 2;
    private static final float SEND_REG_USER = 2.1f;

    private static final int PIANO = 3;

    private static final int SOCIAL = 4;
    private static final float SEARCH_USER = 4.1f;
    private static final float ADD_USER = 4.2f;

    //Controller
    private Controller controller;

    //Connection elements
    private Socket server;
    private DataOutputStream dOut;
    private DataInputStream dIn;
    private ObjectOutputStream obOut;
    private ObjectInputStream obIn;

    /**
     * Client sockets controller
     */
    public ClientConnection(Controller controller) {
        this.controller = controller;
    }

    /**
     * As a user, we make a request to the server to establish connection
     */
    public void establishConnection() {
        try{
            server = new Socket(IP, PORT);
            System.out.println("[CLIENT] Connection established");

            dOut = new DataOutputStream(server.getOutputStream());
            dIn = new DataInputStream(server.getInputStream());

            obOut = new ObjectOutputStream(server.getOutputStream());
            obIn = new ObjectInputStream(server.getInputStream());

        } catch (IOException e) {
            System.out.println("Error to set up connection with the server.");
            e.printStackTrace();
        }

    }
}



