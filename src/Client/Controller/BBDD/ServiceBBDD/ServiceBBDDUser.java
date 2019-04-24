package Client.Controller.BBDD.ServiceBBDD;

import Client.Controller.BBDD.DAOBBDD.DAOUser;
import Client.Controller.BBDD.MultiConnection.AvaiableClients;
import Client.Controller.BBDD.MultiConnection.ClientContextHolder;
import Client.Controller.BBDD.Resources.BBDDException;
import Client.Controller.BBDD.Resources.FieldsNoValidException;
import Client.Model.Song;
import Client.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServiceBBDDUser {

    //The service is in charge of communicating with the dao (it is like the traductor between controller and dao)
    @Autowired
    private DAOUser dao;


    //Methods

    // Pseudo ->
    // Check if the user exists (in the database)
    // If it does not exist:
    // Add to the database

    // If it exist:
    // Error message with JDialog (Controller funciton)

    public void createUser (String username, String password, String photoPath, String email) throws Exception {
        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ClientContextHolder.set(AvaiableClients.noUserSmartPiano);
            dao.checkExistenceUserDatabase(username, password);
            dao.insertUserTable(username, password, photoPath, email);
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //If the user wants to change his information (list of friends updated...), this method doesn't controll the change of username
    public void modifyInformationUser (User user){
        ClientContextHolder.set(AvaiableClients.UserRegistered);
        dao.updateUserTable(user);
        ClientContextHolder.clear();
    }



    //If the user wants to add a song (the id is assigned by the database because it's serial
    public void insertSong (String name, int duration, String description, User author, int plays, String filePath) throws Exception {
        Song song  = new Song(name,duration,description,author,plays,filePath);
        if (duration == 0 || author == null || filePath.equals("") || filePath.contains(" ")) {
            throw new FieldsNoValidException();
        }
        else {
            dao.insertSong (song);
        }
    }



    public List<Song> getSongsUser (String username) throws Exception{
        if (username.equals("") || username.contains(" ")) {
            ClientContextHolder.set(AvaiableClients.UserRegistered);
            dao.checkExistenceUserDatabaseWithoutPassword(username);
            List <Song> songs = new ArrayList<>();
            List <Song> songsSystem  = dao.getSystemSongs();
            for (int i = 0; i < songsSystem.size();i++) {
                songs.add(songsSystem.get(i));
            }
            List <String> friends = dao.getSomeoneFriends(username);
            for (int j = 0; j < friends.size(); j++) {
                List <Song> songsFriend = dao.getSomeoneSongs(friends.get(j));
                for (int u = 0; u < songsFriend.size();u++) {
                    songs.add(songsFriend.get(u));
                }
            }
            ClientContextHolder.clear();
            return songs;
        }
        else {
            throw new FieldsNoValidException();
            return null;
        }
    }


    public void InsertOrUpdateSystemInformation (String name, Date date, int totalUsers) {

    }

    //Getters and setters (you should not use it, it's only for the injection)
    public DAOUser getDao() {
        return dao;
    }

    public void setDao(DAOUser dao) {
        this.dao = dao;
    }
}
