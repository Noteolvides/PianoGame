package Server.Network;

import Model.Song;
import Model.User;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import Server.Controller.RegisterController;
import Server.Controller.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.sound.midi.InvalidMidiDataException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DedicatedServer extends Thread {
    private Socket socket;
    private Server server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean running;
    private String userSave;
    private String friendSave;

    private static final int CONFIRMATION = 0;
    private static final int ERROR = -1;
    private static final int ERROR_BBDD = 1;
    private static final int ERROR_MIDI = 2;
    private static final int ERROR_OBJECT = 3;
    private static final String GO_BACK = "go_back";
    private ServiceBBDDServer service;

    public static final String LOGIN = "login";
    public static final String CHECK_USUARIO = "log_user";

    public static final String REGISTER = "register";
    public static final String CHECK_REGISTER = "reg_user";

    public static final String PIANO = "piano";

    public static final String SOCIAL = "social";
    public static final String SEARCH_USER = "search_user";
    public static final String ADD_USER = "add_user";

    public static final String LOG_OUT = "log_out";
    public static final String DELETE_ACCOUNT = "delete_account";

    public static final String SELECT_SONG = "select_song";
    public static final String SAVE_SONG = "save_song";
    public static final String REQUEST_SONG = "request_song";

    /**
     * Server sockets thread controller
     */
    public DedicatedServer(ServiceBBDDServer service){
        this.service = service;
    }

    /**
     * Establish the connection with user
     * @throws IOException: In case that the Streams couldn't be made, the function throws an exception
     */
    public void startDedicatedServer() throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        running = true;
        start();
    }

    /**
     * Stops the server thread
     */
    public void stopDedicatedServer() {
        running = false;
        this.interrupt();
    }

    @Override
    /**
     * Waits for requests from the user to access to one function or into another
     */
    public void run() {

        while (running && !isInterrupted()) {
            try {
                switch (dataInputStream.readUTF()) {
                    //Login
                    case LOGIN:
                        loginComunication();
                        break;
                    //Register
                    case REGISTER:
                        registerComunication();
                        break;
                    //PlayPiano
                    case PIANO:
                        pianoComunication();
                        break;
                    //Social
                    case SOCIAL:
                        socialComunication();
                        break;
                    case LOG_OUT:
                        logOut();
                        break;
                    case DELETE_ACCOUNT:
                        deleteAccount();
                        break;
                    default:
                        //Nothing
                        break;
                }
            } catch (IOException e) {
                //When the client interrupts the communication
                System.out.println("Connection lost with the client");
                stopDedicatedServer();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function that controls the login communication from user
     * @throws IOException: If there is any error related to connections throws exception
     * @throws ClassNotFoundException: If is any error with the User object throws exception
     */
    private void loginComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                case CHECK_USUARIO:
                    //This will be the object to read and
                    User user = (User) objectInputStream.readObject();

                    //Then we want to check if the object Exist in the database
                    try {
                        //Here we make a  query to de database
                        service.getInstanceOfAUserByName(user.getNameUser(),user.getPassword());
                        service.addConnection();
                        //If the query return true
                        dataOutputStream.writeInt(CONFIRMATION);
                        userSave = user.getNameUser();
                        //Else
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR);
                    }
                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    /**
     * Disconnects the user from server
     * @throws IOException: If there is any error related to connections throws exception
     */
    private void logOut() throws IOException {
        System.out.println("Closing connection with client");
        try{
            dataOutputStream.writeInt(CONFIRMATION);
        } catch (IOException e) {
            dataOutputStream.writeInt(ERROR);
        }
    }

    /**
     * Deletes the account from BBDD of the user and disconnects the user from server
     * @throws IOException: If there is any error related to connections throws exception
     */
    private void deleteAccount() throws IOException{
        System.out.println("Deleting user");
        try{
            service.deleteUser(userSave);
            dataOutputStream.writeInt(CONFIRMATION);
        } catch (IOException e) {
            dataOutputStream.writeInt(ERROR);
        } catch (BBDDException e) {
            dataOutputStream.writeInt(ERROR_BBDD);
        }
    }

    /**
     * Function that controls all the functionalities related to the piano client functions
     * @throws IOException: If there is any error related to connections throws exception
     */
    private void pianoComunication() throws IOException {
        boolean goBack = false;
        while (!goBack){
            switch (dataInputStream.readUTF()){
                //Return all the songs that the user has access to
                case SELECT_SONG:
                    try {
                        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                        List<Song> songs = service.getSongsUser(userSave);
                        String songsJson = gson.toJson(songs);
                        dataOutputStream.writeUTF(songsJson);
                        dataOutputStream.writeInt(CONFIRMATION);
                    } catch (Exception e) {
                        dataOutputStream.writeInt(ERROR);   //Error al recuperar les cançons
                    }
                    break;
                //Save a song into the BBDD played and composed by a user
                case SAVE_SONG:
                    try {
                        String song = dataInputStream.readUTF();
                        System.out.println("Llego aqui");

                        //En el server guardaremos todas las canciones, de esta forma, tendremos una carpeta con todas las canciones
                        //o aun mejor, una carpeta y dentro de esa carpeta varias subcarpetas con las canciones de cada usuario, y que dentro
                        //de esa carpeta tambien este la imagen del usuario

                        Song s = (Song) objectInputStream.readObject();
                        String direction;

                        //If privacity = true: song is private, else: song is public
                        if (s.getPrivacity()) {
                            direction =  "FilesBBDD/Private/" + userSave;
                        } else {
                            direction =  "FilesBBDD/Public/" + userSave;
                        }

                        File directorio = new File(direction);
                        boolean dirCreated = directorio.mkdirs();


                        MidiFileManager.savePatternToMidi(new Pattern(song), new File(directorio + "/" + s.getTitle()));
                        s.setFilePath(directorio + "/" + s.getTitle());
                        service.insertSongFromUser(s);
                        System.out.println("He guardat: " + s.toString() + " ------- " + song);
                        dataOutputStream.writeInt(CONFIRMATION);
                    } catch (IOException e) {
                        dataOutputStream.writeInt(ERROR);   //Error al connectar amb el servidor
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR_BBDD); //Error amb la BBDD
                    } catch (ClassNotFoundException e) {
                        dataOutputStream.writeInt(ERROR_OBJECT); //Error amb l'estructura de l'objecte
                    }
                    break;
                //Return the midi file of an specific song that the user wants to play
                case REQUEST_SONG:
                    try {
                        String song = dataInputStream.readUTF();
                        //I return the song, so i can get the path i then i can get the song and pass it
                        Song songObtained = service.getConcreteSongUser(userSave,song);
                        songObtained.setPlays(songObtained.getPlays() + 1);
                        service.updateSong(songObtained);
                        String pathSong = songObtained.getFilePath();
                        Pattern pattern = MidiFileManager.loadPatternFromMidi(new File(pathSong));
                        dataOutputStream.writeUTF(pattern.toString());

                        dataOutputStream.writeInt(CONFIRMATION);
                    } catch (IOException e) {
                        dataOutputStream.writeInt(ERROR);
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR_BBDD);
                    } catch (InvalidMidiDataException e) {
                        dataOutputStream.writeInt(ERROR_MIDI);   //Error amb l'arxiu MIDI
                    }
                    break;
                //The user has exit the piano view
                case GO_BACK:
                    goBack = true;
                    break;
            }
        }
    }

    /**
     * Function that register a new user into the BBDD
     * @throws IOException: If there is any error related to connections throws exception
     * @throws ClassNotFoundException: If is any error with the User object throws exception
     */
    private void registerComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                //Check all the attributes of the user and register it in the BBDD
                case CHECK_REGISTER:
                    //This will be the object to read and
                    User user = (User) objectInputStream.readObject();
                    //Then we want to check if the object The new User has been inserted
                    Utils utils = new Utils();
                    if (utils.confirmPassword(user.getPassword(), user.getPassword(), user.getNameUser())) {
                        try {
                            service.createUserFromNoUser(user);
                            //If the query return true
                            dataOutputStream.writeInt(CONFIRMATION);
                        } catch (BBDDException e) {
                            dataOutputStream.writeInt(ERROR_BBDD);
                        }
                    } else {
                        dataOutputStream.writeInt(ERROR);
                    }

                    break;
                //The user has exit the register view
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    /**
     * Function that controls all the functionalities related to the social client functions
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void socialComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                //Search an specific user
                case SEARCH_USER:
                    //This will be the object to read and
                    friendSave = dataInputStream.readUTF();

                    //Then we want to check if the object Exist in the database
                    try {
                        User userTosend = service.searchUserByCode(friendSave);
                        friendSave = userTosend.getNameUser();
                        if (service.checkUserRelationship(userSave,friendSave)){
                            userTosend.setPassword("YES");
                        }else{
                            userTosend.setPassword("NO");
                        }
                        dataOutputStream.writeInt(CONFIRMATION);
                        objectOutputStream.writeObject(userTosend);
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR_BBDD);
                        System.out.println("Se fue a la puta");
                    }
                    break;
                //Add the searched user
                case ADD_USER:
                    //Query to make friends
                    try {
                        User user1 = service.searchUserByUsername(userSave);
                        User user2 = service.searchUserByUsername(friendSave);
                        user1.getFollowing().add(user2);
                        service.updateInformationUser(user1);

                        dataOutputStream.writeInt(CONFIRMATION);
                    }catch (BBDDException e){
                        dataOutputStream.writeInt(ERROR_BBDD);
                    }
                    break;
                //The user has exit the social view
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    public ServiceBBDDServer getService() {
        return service;
    }

    public void setService(ServiceBBDDServer service) {
        this.service = service;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
