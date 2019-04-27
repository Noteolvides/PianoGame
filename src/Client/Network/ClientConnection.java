package Client.Network;

import Client.Controller.Controller;
import Model.User;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread{
    //Connection const.
    private static final int PORT = 5000;
    private static final String IP = "localhost";

    private static final int RECEIVED = 0;
    private static final int ERROR = -1;
    private static final String GO_BACK = "go_back";

    public static final String LOGIN = "login";
    public static final String SEND_LOG_USER = "log_user";

    public static final String REGISTER = "register";
    public static final String SEND_REG_USER = "reg_user";

    public static final String PIANO = "piano";

    public static final String SOCIAL = "social";
    public static final String SEARCH_USER = "search_user";
    public static final String ADD_USER = "add_user";

    //Controller
    private Controller controller;

    //Connection elements
    private Socket server;
    private DataOutputStream dOut;
    private DataInputStream dIn;
    private ObjectOutputStream obOut;
    private ObjectInputStream obIn;

    boolean running;
    String nextFunc;

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
        running = true;

        while (running && !isInterrupted()) {
            //TODO: falta que el controller faci un setter cada vegada que fa una de les funcions
            switch (nextFunc) {
                case LOGIN:
                    loginUser();
                    break;
                case REGISTER:
                    registerUser();
                    break;
                case PIANO:
                    break;
                case SOCIAL:
                    searchUser();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                default:
                    //Nothing
                    break;
            }
        }

    }

    // Login/Register functions
    public void loginUser() {
        int trans_estate = RECEIVED;
        //Todo: falta demanar l'usuari del controller

        try{
            //We sent to the server the current operation
            dOut.writeUTF(LOGIN);
            dOut.writeUTF(SEND_LOG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getLogin());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't connect to server");
                //TODO, QUE SALTI UN DIALOG
            } else {
                dOut.writeUTF(GO_BACK);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser() {
        int trans_estate = RECEIVED;

        try{
            //We sent to the server the current operation
            dOut.writeUTF(REGISTER);
            dOut.writeUTF(SEND_REG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getRegister());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't connect to server");
                //TODO, QUE SALTI UN DIALOG
            } else {
                System.out.println("WELCOME, WELCOME!");
                dOut.writeUTF(GO_BACK);

            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOut() {
        try {
            dOut.writeUTF(GO_BACK);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        try {
            dOut.writeUTF(GO_BACK);
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Social functions
    public void searchUser() {
        int trans_estate = RECEIVED;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(SOCIAL);
            dOut.writeUTF(SEARCH_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getSearchedUser());
            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();
            //Todo, potser no li hem d'enviar un usuari, sino un String amb el nom de l'usuari i rebre el usuari com objecte

            if (trans_estate == ERROR) {
                System.out.println("Error, this user doesn't exists");
                //TODO, QUE SALTI UN DIALOG
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser() {
        int trans_estate = RECEIVED;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(ADD_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getAddedUser());
            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();
            //Todo, potser no li hem d'enviar un usuari, sino un String amb el nom de l'usuari i rebre el usuari com
            // objecte que es el que afegirem com amic del client

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

    public void exitSocial() throws IOException {
        try {
            //We sent to the server the current operation
            dOut.writeUTF(GO_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Piano functions


    public String getNextFunc() {
        return nextFunc;
    }

    public void setNextFunc(String nextFunc) {
        this.nextFunc = nextFunc;
    }
}



