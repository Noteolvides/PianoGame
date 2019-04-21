package Client.Network;

import Client.Controller.Controller;
import Server.Model.User;

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
    private static final int SEND_REG_USER = 1;

    private static final int PIANO = 3;

    private static final int SOCIAL = 4;
    private static final int SEARCH_USER = 1;
    private static final int ADD_USER = 2;

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
     * Close connection with the server and interrupts the server Thread connection
     */
    public void closeConnection() {
        running = false;
        interrupt();
    }

    /**
     * Each user have his own connection Thread to communicate with the server while it uses the client SmartPiano
     */
    public void run() {
        while (running && !isInterrupted()) {
            /*try{
                //Todo, aqui que cullons haig de ficar
            }catch ();*/
        }
    }

    // Login/Register functions
    public void loginUser(User user) {
        int trans_estate = RECEIVED;

        try{
            //We sent to the server the current operation
            dOut.writeInt(LOGIN);
            dOut.writeInt(SEND_LOG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(user);
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't connect to server");
                //TODO, QUE SALTI UN DIALOG
            } else {
                dOut.writeInt(GO_BACK);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser(User user) {
        int trans_estate = RECEIVED;

        try{
            //We sent to the server the current operation
            dOut.writeInt(REGISTER);
            dOut.writeInt(SEND_REG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(user);
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't connect to server");
                //TODO, QUE SALTI UN DIALOG
            } else {
                System.out.println("WELCOME, WELCOME!");
                dOut.writeInt(GO_BACK);

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        try {
            dOut.writeInt(GO_BACK);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        try {
            dOut.writeInt(GO_BACK);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Social functions
    public void searchUser(User user) {
        int trans_estate = RECEIVED;

        try {
            //We sent to the server the current operation
            dOut.writeInt(SOCIAL);
            dOut.writeInt(SEARCH_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(user);
            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, this user doesn't exists");
                //TODO, QUE SALTI UN DIALOG
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        int trans_estate = RECEIVED;

        try {
            //We sent to the server the current operation
            dOut.writeInt(ADD_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(user);
            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't connect to server");
                //TODO, QUE SALTI UN DIALOG
            } else {
                //TODO, QUE SALTI UN DIALOG
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Todo, cada vegad a que surto d'una finestra haig d'enviar un goback per separat o com?

    // Piano functions
}



