package Client.Controller.BBDD.ServiceBBDD;

import Client.Controller.BBDD.DAOBBDD.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceBBDDUser {
    @Autowired
    private DAOUser dao;


    public DAOUser getDao() {
        return dao;
    }

    public void setDao(DAOUser dao) {
        this.dao = dao;
    }
}
