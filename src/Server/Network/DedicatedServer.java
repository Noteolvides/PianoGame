package Server.Network;

import Model.Song;
import Model.User;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

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

    public static final String SELECT_SONG = "select_song";
    public static final String SAVE_SONG = "save_song";


    public DedicatedServer(ServiceBBDDServer service){
        this.service = service;
    }

    public void startDedicatedServer() throws IOException {
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        running = true;
        start();
    }

    public void stopDedicatedServer() {
        running = false;
        this.interrupt();
    }

    @Override
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
                        break;
                    default:
                        //Nothing
                        break;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void loginComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                case CHECK_USUARIO:
                    //This will be the object to read and
                    User user = (User) objectInputStream.readObject();

                    //Then we want to check if the object Exist in the database
                    try {
                        service.getInstanceOfAUser(user.getNameUser(), user.getPassword());
                        //Here we make a  query to de databas
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

    private void pianoComunication() throws IOException {
        boolean goBack = false;
        while (!goBack){
            switch (dataInputStream.readUTF()){
                case SELECT_SONG:
                    try {
                        Gson gson = new Gson();
                        ArrayList<Song> songs = (ArrayList<Song>) service.getSongsUser(userSave);
                        dataOutputStream.writeUTF(gson.toJson(songs));
                        dataOutputStream.writeInt(CONFIRMATION);

                    } catch (Exception e) {
                        dataOutputStream.writeInt(ERROR);
                    }
                    break;
                case SAVE_SONG:
                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    private void registerComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                case CHECK_REGISTER:
                    //This will be the object to read and
                    User user = (User) objectInputStream.readObject();
                    //Then we want to check if the object The new User has been inserted
                    try {
                        service.createUserFromNoUser(user);
                        //Here we make a  query to de databas
                        //If the query return true
                        dataOutputStream.writeInt(CONFIRMATION);
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR);
                    }


                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    private void socialComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readUTF()) {
                case SEARCH_USER:
                    //This will be the object to read and
                    friendSave = dataInputStream.readUTF();
                    //Then we want to check if the object Exist in the database

                    //Here we make a  query to de databas that returns the user
                    try {
                        User userTosend = service.searchUser(friendSave);
                        friendSave = userTosend.getNameUser();
                        if (service.checkUserRelationship(userSave,friendSave)){
                            userTosend.setPassword("YES");
                        }else{
                            userTosend.setPassword("NO");
                        }
                        dataOutputStream.writeInt(CONFIRMATION);
                        objectOutputStream.writeObject(userTosend);
                    } catch (BBDDException e) {
                        dataOutputStream.writeInt(ERROR);
                        System.out.println("Se fue a la puta");
                    }
                    break;
                case ADD_USER:
                    //Query to make friends
                    try {
                        User user1 = service.searchUser(userSave);
                        User user2 = service.searchUser(friendSave);
                        user1.getFollowing().add(user2);
                        service.updateInformationUser(user1);

                        dataOutputStream.writeInt(CONFIRMATION);
                    }catch (BBDDException e){
                        dataOutputStream.writeInt(ERROR);
                    }
                    break;
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
