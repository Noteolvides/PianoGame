package Client.Controller.BBDD;

import Client.Controller.BBDD.ServiceBBDD.ServiceBBDDUser;
import org.springframework.beans.factory.annotation.Autowired;

public class ControllerProva {
    @Autowired
    private ServiceBBDDUser service;


    public ServiceBBDDUser getService() {
        return service;
    }

    public void setService(ServiceBBDDUser service) {
        this.service = service;
    }
}
