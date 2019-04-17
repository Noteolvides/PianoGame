package Server.Network;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

    public void stopServer(){
        running = false;
        this.interrupt();
        try {
            serverSocket.close();
            for (DedicatedServer ds : dedicatedServers){
                ds.stopDedicatedServer();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        while (running){
            try {
                System.out.println("Waiting for a new Pianist..");
                Socket socket = serverSocket.accept();
                dedicatedServers.add(new DedicatedServer(socket,this));
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
