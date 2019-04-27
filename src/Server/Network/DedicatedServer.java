package Server.Network;

import java.io.*;
import java.net.Socket;

public class DedicatedServer extends Thread {
    private Socket socket;
    private Server server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean running;

    //TODO: cambiar valores constantes
    private static final int CONFIRMATION = 0;
    private static final int ERROR = -1;
    private static final String GO_BACK = "go_back";

    public static final String LOGIN = "login";
    public static final String CHECK_USUARIO = "log_user";

    public static final String REGISTER = "register";
    public static final String CHECK_REGISTER = "reg_user";

    public static final String PIANO = "piano";

    public static final String SOCIAL = "social";
    public static final String SEARCH_USER = "search_user";
    public static final String ADD_USER = "add_user";

    public DedicatedServer(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
        startDedicatedServer();
    }

    private void startDedicatedServer() throws IOException {
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
                    objectInputStream.readObject();
                    //Then we want to check if the object Exist in the database

                    //Here we make a  query to de databas
                    //If the query return true
                    dataOutputStream.writeInt(CONFIRMATION);
                    //Else
                    dataOutputStream.writeInt(ERROR);
                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    private void pianoComunication() {
    }

    private void registerComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack){
            switch (dataInputStream.readUTF()) {
                case CHECK_REGISTER:
                    //This will be the object to read and
                    objectInputStream.readObject();
                    //Then we want to check if the object The new User has been inserted

                    //Here we make a  query to de databas
                    //If the query return true
                    dataOutputStream.writeInt(CONFIRMATION);
                    //Else
                    dataOutputStream.writeInt(ERROR);
                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }

    private void socialComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack){
            switch (dataInputStream.readUTF()) {
                case SEARCH_USER:
                    //This will be the object to read and
                    objectInputStream.readObject();
                    //Then we want to check if the object Exist in the database

                    //Here we make a  query to de databas that returns the user
                    //If the query return true
                    dataOutputStream.writeInt(CONFIRMATION);
                    //Here we send the user;
                    objectOutputStream.write((Integer) new Object());
                    //Else
                    dataOutputStream.writeInt(ERROR);
                    break;
                case GO_BACK:
                    goBack = true;
            }
        }
    }
}
