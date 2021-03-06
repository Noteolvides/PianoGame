package Client.Network;

import Client.Controller.Controller;
import Model.Song;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * This class use Sockets to connect with the server.
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
public class ClientConnection extends Thread {
    //Connection const.
    private int port = 5000;
    private String IP = "localhost";

    public static final int OK = 0;
    public static final int KO = -1;
    public static final int ERROR_BBDD = 1;
    public static final int ERROR_MIDI = 2;
    public static final int ERROR_OBJECT = 3;

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

    public static final String SELECT_SONG = "select_song";
    public static final String SAVE_SONG = "save_song";
    public static final String REQUEST_SONG = "request_song";
    public static final String EXIT_PIANO = "exit_piano";
    public static final String GO_BACK = "go_back";

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
     * Inicialization of the client
     * @param controller The main controller
     * @param clientPort port of the conection
     * @param ip the ip of the server
     */
    public ClientConnection(Controller controller, int clientPort, String ip) {
        port = clientPort;
        this.controller = controller;
        nextFunc = "";
        this.IP = ip;
    }

    /**
     * As a user, we make a request to the server to establish connection
     */
    public void establishConnection() {
        try {
            server = new Socket(IP, port);
            System.out.println("[CLIENT] Connection established");

            obOut = new ObjectOutputStream(server.getOutputStream());
            obIn = new ObjectInputStream(server.getInputStream());
            dOut = new DataOutputStream(server.getOutputStream());
            dIn = new DataInputStream(server.getInputStream());

            running = true;

        } catch (IOException e) {
            System.out.println("Error to set up connection with the server.");
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
                case LOG_OUT:
                    logOut();
                    break;
                case DELETE_ACCOUNT:
                    deleteUser();
                    break;
                case PIANO:
                    openPiano();
                    break;
                case SELECT_SONG:
                    selectSongs();
                    break;
                case SAVE_SONG:
                    saveSong();
                    break;
                case REQUEST_SONG:
                    requestSong();
                    break;
                case EXIT_PIANO:
                    exitPiano();
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
                default:
                    //Nothing
                    break;
            }
            nextFunc = "Nothing";
        }
    }

    //Login/Register Functions

    /**
     * Dedicated function to login a user
     */
    public void loginUser() {
        int trans_estate = KO;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(LOGIN);
            dOut.writeUTF(SEND_LOG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getLogin());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();
            User actualUser = (User) obIn.readObject();
            controller.networkLogInResult(trans_estate, actualUser);
            if (trans_estate == OK) {
                dOut.writeUTF(GO_BACK);
            }

        } catch (IOException | ClassNotFoundException e) {
            controller.networkLogInResult(trans_estate, null);
        }

    }

    /**
     * Dedicated function to register a user
     */
    public void registerUser() {
        int trans_estate = KO;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(REGISTER);
            dOut.writeUTF(SEND_REG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getRegister());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();
            controller.networkRegisterResult(trans_estate);
            if (trans_estate == OK) {
                dOut.writeUTF(GO_BACK);
            }

        } catch (IOException e) {
            controller.networkRegisterResult(KO);
        }
    }

    /**
     * LogOut of the account connected to the Application
     */
    public void logOut() {
        int trans_estate = KO;
        try {
            dOut.writeUTF(LOG_OUT);

            trans_estate = dIn.readInt();
            controller.networkLogOutResult(trans_estate);
            if (trans_estate == OK) {
                dOut.writeUTF(GO_BACK);
            }

        } catch (IOException e) {
            controller.networkLogOutResult(KO);
        }
    }

    /**
     * Delete the account from de BBDD of the Application
     */
    public void deleteUser() {
        int trans_estate = KO;
        try {
            dOut.writeUTF(DELETE_ACCOUNT);

            trans_estate = dIn.readInt();
            controller.networkDeleteAccountResult(trans_estate);
            if (trans_estate == OK) {
                dOut.writeUTF(GO_BACK);
            }
        } catch (IOException e) {
            controller.networkDeleteAccountResult(KO);
        }
    }
    //END Login/Register Functions

    //Social Functions

    /**
     * Warn the server that the user opened the Social Windows
     */
    public void openSocialWindow() {
        //We sent to the server the current operation
        try {
            dOut.writeUTF(SOCIAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The user Sarch another user for his/her name, and waits for a response from server
     */
    public void searchUser() {
        int trans_estate = KO;

        try {
            dOut.writeUTF(SEARCH_USER);
            //This will send the User Object that we want to log into our server
            dOut.writeUTF(controller.getSearchedUser());

            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();
            User userToController;
            if (trans_estate == OK) {
                userToController = (User) obIn.readObject();
            } else {
                userToController = null;
            }
            controller.networkSearchSocialResult(trans_estate, userToController);
        } catch (IOException | ClassNotFoundException e) {
            controller.networkSearchSocialResult(KO, null);
        }
    }

    /**
     * The user adds the searched user to his/her list of friends
     */
    public void addUser() {
        int trans_estate = KO;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(ADD_USER);

            //We wait for response if the operation is completed correctly
            trans_estate = dIn.readInt();
            controller.networkAddSocialResult(trans_estate);
        } catch (IOException e) {
            controller.networkAddSocialResult(KO);
        }
    }

    /**
     * Warn the server that the user exit the Social Windows
     */
    public void exitSocial() {
        try {
            //We sent to the server the current operation
            dOut.writeUTF(GO_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //END Social Functions

    //Piano Functions
    /**
     * Warn the server that the user opened the Piano Windows
     */
    public void openPiano() {
        //We sent to the server the current operation
        try {
            dOut.writeUTF(PIANO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The user entered to JSong window and this function requests to server all the songs that the user has access
     */
    private void selectSongs() {
        int trans_estate = KO;
        try {
            dOut.writeUTF(SELECT_SONG);

            Gson gson = new Gson();
            String songsString;
            songsString =  dIn.readUTF();
            ArrayList<Song> songs = gson.fromJson(songsString, new TypeToken<ArrayList <Song>>(){}.getType());

            trans_estate = dIn.readInt();
            controller.networkSelectSongResult(trans_estate, songs);
        } catch (IOException e) {
            controller.networkSelectSongResult(KO, null);
        }

    }

    /**
     * The user wants to save a song that has created
     */
    private void saveSong() {
        int trans_estate = KO;
        try {
            String songFile = controller.getSongMidi();
            Song song = controller.getSongToSave();
            dOut.writeUTF(SAVE_SONG);
            dOut.writeUTF(songFile);
            obOut.writeObject(song);

            trans_estate = dIn.readInt();
            controller.networkSaveSongResult(trans_estate);
        } catch (IOException e) {
            controller.networkSaveSongResult(KO);
        }
    }

    /**
     * User request one song to play, from all the songs that has access
     */
    private void requestSong() {
        int trans_estate = KO;
        try {
            dOut.writeUTF(REQUEST_SONG);
            String song = controller.getSongToPlay();
            String author = controller.getSongUserToPlay();
            dOut.writeUTF(song);
            dOut.writeUTF(author);

            String midi =  dIn.readUTF();

            trans_estate = dIn.readInt();
            controller.networkRequestSongResult(trans_estate, midi);
        } catch (IOException e) {
            controller.networkRequestSongResult(KO, null);
        }
    }

    /**
     * Warn the server that the user exit the Piano Windows
     */
    public void exitPiano() {
        try {
            //We sent to the server the current operation
            dOut.writeUTF(GO_BACK);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // END Piano Functions

    public String getNextFunc() {
        return nextFunc;
    }

    public void setNextFunc(String nextFunc) {
        this.nextFunc = nextFunc;
    }


}
