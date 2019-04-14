package Client.Controller.Network;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
    //Connection const.
    private static final int PORT = 5000;
    private static final String IP = "localhost";

    private static final int RECEIVED = 0;
    private static final int ERROR = -1;

    //Controller

    //Connection elements
    Socket server;

    /**
     * Client sockets controller
     */
    public ClientConnection() {

    }

    /**
     * As a user, we make a request to the server to establish connection
     */
    public void establishConnection() {
        try{
            server = new Socket(IP, PORT);
            System.out.println("[CLIENT] Connection established");

            //Todo, establir camins de connexions

        } catch (IOException e) {
            System.out.println("Error to set up connection with the server.");
            e.printStackTrace();
        }

    }
}



