package Server.Controller.BBDD.ServiceBBDD;

import Server.Controller.BBDD.MultiConnection.AvaiableClients;
import Server.Controller.BBDD.MultiConnection.ServerContextHolder;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.Resources.FieldsNoValidException;
import Server.Controller.BBDD.DAOBBDD.DAOServer;
import Server.Model.Song;
import Server.Model.System;
import Server.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceBBDDServer {
    @Autowired
    private DAOServer dao;


    public void createUser (String username, String password, String photoPath, String email) throws Exception {
        // Pseudo ->
        // Check if the user exists (in mysql and in the database)
        // If it does not exist:
        // Add to mysql
        // Add to the database

        // If it exist:
        // Error message with JDialog (Controller funciton)

        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.adminGeneral);
            dao.checkExistenceUserMysql(username, password);
            ServerContextHolder.clear();
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabase(username, password);
            dao.addUserIntoMysql(username, password);
            dao.insertUserTable(username, password, photoPath, email);

        }
        else {
            throw new FieldsNoValidException();
        }
    }

    public void insertSong (String name, int duration, String description, int plays, String filePath, System system) throws Exception {
        Song song = new Song(name, duration,description,plays,filePath,system);
        if (name.equals("") || name.contains(" ") || filePath.equals("") || filePath.contains(" ")) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkSongExistence(name);
            dao.insertSong(song);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }


    public void deleteSong (String nameOfTheSong) throws FieldsNoValidException{
        try {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkSongExistence(nameOfTheSong);
            ServerContextHolder.clear();
            throw new FieldsNoValidException();

        } catch (BBDDException e) {
            dao.deleteSong (nameOfTheSong);
            ServerContextHolder.clear();
        }
    }



    //Getters and setters
    public DAOServer getDao() {
        return dao;
    }

    public void setDao(DAOServer dao) {
        this.dao = dao;
    }
}
