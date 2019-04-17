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

    private static final int CONFIRMATION = 0;
    private static final int ERROR = -1;

    private static final int LOGIN = 1;
    private static final int CHECK_USUARIO = 1;
    private static final int GO_BACK = -1;

    private static final int REGISTER = 2;
    private static final int SEND_REG_USER = 1;

    private static final int PIANO = 3;

    private static final int SOCIAL = 4;
    private static final int SEARCH_USER = 1;
    private static final int ADD_USER = 2;

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
                switch (dataInputStream.readInt()) {
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
            }
        }
    }

    private void loginComunication() throws IOException, ClassNotFoundException {
        boolean goBack = false;
        while (!goBack) {
            switch (dataInputStream.readInt()) {
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

    private void registerComunication() {
    }

    private void socialComunication() {
    }
}
