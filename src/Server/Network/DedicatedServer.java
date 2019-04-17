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
            try {
                int option = dataInputStream.readInt();
                switch (option){
                        //Login
                    case 1:

                        break;
                        //Register
                    case 2:
                        break;
                        //PlayPiano
                    case 3:
                        break;
                        //Social
                    case 4:
                        break;
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
