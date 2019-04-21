package Client.Controller.BBDD.ServiceBBDD;

import Client.Controller.BBDD.DAOBBDD.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceBBDDUser {

    //The service is in charge of communicating with the dao (it is like the traductor between controller and dao)
    @Autowired
    private DAOUser dao;


    //Methods

    public void createUser (String username, String password) {
        //Pseudo -->
        //Comprovacion de si existe
        //Si existe:
        //  añadir a mysql
        //  añadir a la base de datos

        //Si no existe:
        //  Mensaje de error
    }









    //Getters and setters (you should not use it, it's only for the injection)

    public DAOUser getDao() {
        return dao;
    }

    public void setDao(DAOUser dao) {
        this.dao = dao;
    }
}
