package Server.Controller.Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread{
    private List<DedicatedServer> dedicatedServers;
    private ServerSocket serverSocket;
    private static final int PORT = 5000;
    private boolean running;

    public Server(){
        dedicatedServers = new LinkedList<>();
        running = false;
    }

    public void startServer() throws IOException{
        serverSocket = new ServerSocket(PORT);
        running = true;
        super.start();
    }
}
