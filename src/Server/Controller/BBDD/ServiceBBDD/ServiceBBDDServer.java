package Server.Controller.BBDD.ServiceBBDD;

import Server.Controller.BBDD.DAOBBDD.DAOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBBDDServer {
    @Autowired
    private DAOServer dao;







    //Getters and setters
    public DAOServer getDao() {
        return dao;
    }

    public void setDao(DAOServer dao) {
        this.dao = dao;
    }
}
