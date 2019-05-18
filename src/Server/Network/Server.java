package Server.Network;


import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server extends Thread{
    private List<DedicatedServer> dedicatedServers;

    @Autowired
    private ServiceBBDDServer serverService;
    private ServerSocket serverSocket;
    private int port;
    private boolean running;


    /**
     * Creation of a server containing a dedicatedServer for each host
     */
    public Server(){
        dedicatedServers = new LinkedList<>();
        running = false;
    }

    /**
     * It starts the server
     * @param portConnexioBBDD: Socket port number
     * @throws IOException
     */
    public void startServer(int portConnexioBBDD) throws IOException{
        this.port = portConnexioBBDD;
        serverSocket = new ServerSocket(portConnexioBBDD);
        running = true;
        super.start();
    }

    /**
     * Stops the server
     */
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

    /**
     * Run method of the thread, it waits for a new host connection
     * After receiving a connection it creates a new DedicatedServer
     * for the host
     */
    @Override
    public void run() {
        while (running){
            try {
                System.out.println("Waiting for a new Pianist..");
                Socket socket = serverSocket.accept();

                DedicatedServer dedicatedServer = new DedicatedServer(serverService);
                dedicatedServer.setSocket(socket);
                dedicatedServer.setServer(this);
                dedicatedServer.startDedicatedServer();
                dedicatedServers.add(dedicatedServer);

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public List<DedicatedServer> getDedicatedServers() {
        return dedicatedServers;
    }

    public ServiceBBDDServer getServerService() {
        return serverService;
    }

    public void setServerService(ServiceBBDDServer serverService) {
        this.serverService = serverService;
    }

}
