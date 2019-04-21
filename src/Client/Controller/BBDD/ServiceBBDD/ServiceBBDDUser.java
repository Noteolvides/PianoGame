package Client.Controller.BBDD.ServiceBBDD;

import Client.Controller.BBDD.DAOBBDD.DAOUser;
import Client.Controller.BBDD.MultiConnection.AvaiableClients;
import Client.Controller.BBDD.MultiConnection.ClientContextHolder;
import Client.Controller.BBDD.Resources.BBDDException;
import Client.Controller.BBDD.Resources.FieldsNoValidException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class ServiceBBDDUser {

    //The service is in charge of communicating with the dao (it is like the traductor between controller and dao)
    @Autowired
    private DAOUser dao;


    //Methods

    public void createUser (String username, String password, String photoPath, String email) throws Exception {
        // Pseudo ->
        // Check if the user exists (in mysql and in the database)
        // If it does not exist:
        // Add to mysql
        // Add to the database

        // If it exist:
        // Error message with JDialog (Controller funciton)

        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ClientContextHolder.set(AvaiableClients.noUserGeneral);
            dao.checkExistenceUserMysql(username, password);
            ClientContextHolder.clear();
            ClientContextHolder.set(AvaiableClients.noUserSmartPiano);
            dao.checkExistenceUserDatabase(username, password);
            dao.addUserIntoMysql(username, password);
            dao.insertUserTable(username, password, photoPath, email);

        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //If the user wants to change his information
    public String modifyInformationUser (String username, String password, String photoPath, String email) {
        ClientContextHolder.set(AvaiableClients.UserRegistered);
        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {

        }
        //Username or password not correct
        return "FieldsError";
    }




    //Getters and setters (you should not use it, it's only for the injection)
    public DAOUser getDao() {
        return dao;
    }

    public void setDao(DAOUser dao) {
        this.dao = dao;
    }
}
