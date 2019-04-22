package Client.Controller.BBDD;

import Client.Controller.BBDD.ServiceBBDD.ServiceBBDDUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerProva {

    //The user can communicate with the service only (never with the damage)
    @Autowired
    private ServiceBBDDUser service;


    public ServiceBBDDUser getService() {
        return service;
    }

    public void setService(ServiceBBDDUser service) {
        this.service = service;
    }
}
