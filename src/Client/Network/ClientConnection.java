package Client.Network;

import Client.Controller.Controller;
import Model.User;

import java.io.*;
import java.net.Socket;

import static Client.Controller.Controller.*;

public class ClientConnection extends Thread{
    //Connection const.
    private static final int PORT = 5000;
    private static final String IP = "localhost";

    private static final int CORRECT = 0;
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
    public static final String EXIT_SOCIAL = "exit_social";

    public static final String DELETE_ACCOUNT = "delete_account";
    public static final String LOG_OUT = "log_out";

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
        nextFunc = "";
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
                    openSocialWindow();
                    break;
                case SEARCH_USER:
                    searchUser();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case EXIT_SOCIAL:
                    exitSocial();
                    break;
                case LOG_OUT:
                    logOut();
                    break;
                case DELETE_ACCOUNT:
                    deleteUser();
                    break;
                default:
                    //Nothing
                    break;
            }
            nextFunc = "Nothing";
        }

    }

    // Login/Register functions
    public void loginUser() {
        int trans_estate = CORRECT;

        try{
            //We sent to the server the current operation
            dOut.writeUTF(LOGIN);
            dOut.writeUTF(SEND_LOG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getLogin());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't login to server");
                controller.networkLogInResult(KO);
            } else {
                controller.networkLogInResult(OK);
                dOut.writeUTF(GO_BACK);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser() {
        int trans_estate = CORRECT;

        try{
            //We sent to the server the current operation
            dOut.writeUTF(REGISTER);
            dOut.writeUTF(SEND_REG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getRegister());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't register to server");
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
    public void openSocialWindow() {
        //We sent to the server the current operation
        try{
            dOut.writeUTF(SOCIAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchUser() {
        int trans_estate = CORRECT;

        try {
            dOut.writeUTF(SEARCH_USER);

            //This will send the User Object that we want to log into our server
            dOut.writeUTF(controller.getSearchedUser());

            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, this user doesn't exists");
                controller.networkSearchSocialResult(KO, null);
            }else{
                User userToController = (User) obIn.readObject();

                if (userToController.getPassword().equals("YES")){
                    controller.networkSearchSocialResult(OK, userToController);
                }else{
                    controller.networkSearchSocialResult(OK, userToController);
                    System.out.println("Pues nos hacemos friends");
                    nextFunc = ADD_USER;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addUser() {
        int trans_estate = CORRECT;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(ADD_USER);

            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("No eres mi amiho");
                controller.networkAddSocialResult(KO);

            } else {
                System.out.println("Si eres mi amiho");
                controller.networkAddSocialResult(OK);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitSocial() {
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



