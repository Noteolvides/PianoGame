package Client.Network;

import Client.Controller.Controller;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread{
    //Connection const.
    private static final int PORT = 5000;
    private static final String IP = "localhost";

    private static final int RECEIVED = 0;
    private static final int ERROR = -1;

    private static final int LOGIN = 1;
    private static final int SEND_LOG_USER = 1;
    private static final int GO_BACK = -1;

    private static final int REGISTER = 2;
    private static final int SEND_REG_USER = 2;

    private static final int PIANO = 3;

    private static final int SOCIAL = 4;
    private static final int SEARCH_USER = 4;
    private static final int ADD_USER = 4;

    //Controller
    private Controller controller;

    //Connection elements
    private Socket server;
    private DataOutputStream dOut;
    private DataInputStream dIn;
    private ObjectOutputStream obOut;
    private ObjectInputStream obIn;

    boolean running;

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

            obOut = new ObjectOutputStream(server.getOutputStream());
            obIn = new ObjectInputStream(server.getInputStream());
            dOut = new DataOutputStream(server.getOutputStream());
            dIn = new DataInputStream(server.getInputStream());

            running = true;

        } catch (IOException e) {
            System.out.println("Error to set up connection with the server.");
            e.printStackTrace();
        }

    }

    /**
     * Each user have his own connection Thread to communicate with the server while it uses the client SmartPiano
     */
    public void run() {
        //TODO, aquí falta el bucle infinit que es pot tancar amd la opcio de log out
        //TODO, tb falta un switch depenent de que fa l'usuari
    }

    // Login/Register functions
    public void loginUser(/*User user*/) {
        try{
            //We sent to the server which
            dOut.writeInt(1);
            //obOut.writeObject(user);


        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser() {

    }

    // Piano functions

    // Social functions
    public void searchUser() {

    }

    public void addUser() {

    }

    // Piano functions
}



