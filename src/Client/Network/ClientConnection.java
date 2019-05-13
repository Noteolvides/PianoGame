package Client.Network;

import Client.Controller.Controller;
import Model.Song;
import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jfugue.pattern.Pattern;

import javax.sound.midi.spi.MidiFileReader;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import static Client.Controller.Controller.*;

public class ClientConnection extends Thread {
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

    public static final String SELECT_SONG = "select_song";
    public static final String SAVE_SONG = "save_song";
    public static final String REQUEST_SONG = "request_song";
    public static final String EXIT_PIANO = "exit_piano";

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
        try {
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
                    //saveSong();
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
        int trans_estate = CORRECT;

        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Dedicated function to register a user
     */
    public void registerUser() {
        int trans_estate = CORRECT;

        try {
            //We sent to the server the current operation
            dOut.writeUTF(REGISTER);
            dOut.writeUTF(SEND_REG_USER);
            //This will send the User Object that we want to log into our server
            obOut.writeObject(controller.getRegister());
            //We wait for response if the information was correct
            trans_estate = dIn.readInt();

            if (trans_estate == ERROR) {
                System.out.println("Error, you couldn't register to server");
                controller.networkLogInResult(KO);
            } else {
                System.out.println("WELCOME, WELCOME!");
                controller.networkLogInResult(OK);
                dOut.writeUTF(GO_BACK);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * LogOut of the account connected to the Application
     */
    public void logOut() {
        int trans_estate = CORRECT;
        try {
            dOut.writeUTF(LOG_OUT);

            trans_estate = dIn.readInt();
            if (trans_estate == ERROR) {
                System.out.println("Couldn't logOut from server");
            } else {
                closeConnection();
            }

        } catch (IOException e) {
            System.out.println("Error trying to log out");
        }
    }

    /**
     * Delete the account from de BBDD of the Application
     */
    public void deleteUser() {
        int trans_estate = CORRECT;
        try {
            //TODO: Msg de confirmacio
            dOut.writeUTF(DELETE_ACCOUNT);

            trans_estate = dIn.readInt();
            if (trans_estate == ERROR) {
                controller.networkDeleteAccountResult(KO);
                System.out.println("Couldn't delete account from server");
            } else {
                controller.networkDeleteAccountResult(OK);
                closeConnection();
            }

        } catch (IOException e) {
            System.out.println("Error trying to delete account.");
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
                controller.networkSearchSocialResult(OK, userToController);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The user adds the searched user to his/her list of friends
     */
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
        int trans_estate = CORRECT;
        try {
            dOut.writeUTF(SELECT_SONG);

            Gson gson = new Gson();
            String songsString;
            songsString =  dIn.readUTF();
            ArrayList<Song> songs = gson.fromJson(songsString, new TypeToken<ArrayList <Song>>(){}.getType());

            trans_estate = dIn.readInt();

            if (trans_estate != ERROR) {
                controller.networkSelectSongResult(OK, songs);

                for (Song s: songs) {
                    System.out.println(s.getTitle());
                }

            }else{
                System.out.println("Error with the GSON songs");
                controller.networkSelectSongResult(KO, null);
            }

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Error with the GSON songs");
        }

    }

    /**
     * The user wants to save a song that has created
     */
    private void saveSong(/*String songFile, Song song*/) {
        int trans_estate = CORRECT;
        try {
            //TODO: BORRAR AIXO
                Song song = new Song();
                String songFile = "Hola bon dia";
            //-----------------
            dOut.writeUTF(SAVE_SONG);
            dOut.writeUTF(songFile);
            obOut.writeObject(song);

            trans_estate = dIn.readInt();
            if (trans_estate == ERROR) {
                //TODO: Controller warn that the song has been saved successfully
            } else {
                //TODO: Controller warn with a JDialog that the Song couldn't be saved. TRY AGAIN
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * User request one song to play, from all the songs that has access
     */
    private void requestSong() {
        int trans_estate = CORRECT;
        try {
            dOut.writeUTF(REQUEST_SONG);
            String song = null; //<- TODO: Controller returns the song or the title of the song that the user wants to play

            dOut.writeUTF(song);
            Pattern midi;
            midi = (Pattern) obIn.readObject(); //TODO: GERARD -> Which object is the Midi File

            trans_estate = dIn.readInt();
            if (trans_estate == ERROR) {
                System.out.println("Error, the song doesn't exist");
                //TODO: Controller warn the user that the songs doesn't exists
            } else {
                //TODO: Controller warns and start reproducing the song
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error trying to access the song");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("File couldn't be read");
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



