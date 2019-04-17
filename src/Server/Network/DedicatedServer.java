package Server.Network;

import java.io.*;
import java.net.Socket;

public class DedicatedServer extends Thread{
    private Socket socket;
    private Server server;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private boolean running;

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
        while (running && !isInterrupted()){

        }
    }
}
