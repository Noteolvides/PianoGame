package Server.Network;


import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    private static final int PORT = 5000;
    private boolean running;
    private final ApplicationContext context;



    public Server(){
        dedicatedServers = new LinkedList<>();
        context =  new ClassPathXmlApplicationContext("Server/Controller/BBDD/Resources/applicationContext_2.xml");
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

                DedicatedServer dedicatedServer = (DedicatedServer) context.getBean("controllerJ2");
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
