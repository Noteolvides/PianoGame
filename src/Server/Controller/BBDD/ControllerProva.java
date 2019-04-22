package Server.Controller.BBDD;

import Server.Controller.BBDD.ServiceBBDD.ServiceBBDDServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerProva {
    @Autowired
    private ServiceBBDDServer service;



    //Getters and setters
    public ServiceBBDDServer getService() {
        return service;
    }

    public void setService(ServiceBBDDServer service) {
        this.service = service;
    }


}
