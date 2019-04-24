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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ServiceBBDDServer {
    @Autowired
    private DAOServer dao;
    //TODO: JUntar usuari i server en un de sol la database

    // Pseudo ->
    // Check if the user exists ( in the database)
    // If it does not exist:
    // Add to the database

    // If it exist:
    // Error message with JDialog (Controller funciton)
    public void createUser (String username, String password, String photoPath, String email) throws Exception {

        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabase(username, password);
            dao.insertUserTable(username, password, photoPath, email);
            ServerContextHolder.clear();
        }
        else {
            ServerContextHolder.clear();
            throw new FieldsNoValidException();
        }
    }

    public void deleteUserByObject (User user) throws Exception {
        if (user != null) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(user.getName());
            dao.deleteUserByObject(user);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    public void deleteUserByName (String username) throws Exception{
        if (username.equals("") || username.contains(" ")) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(username);
            dao.deleteUser(username);
            ServerContextHolder.clear();
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

    //Method to obtain all the songs Regardless of whether they are private or public
    public List<Song> getListSongs () {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        List <Song> result = dao.getAllTheSongs();
        ServerContextHolder.clear();
        return result;
    }

    public List <Song> getTop5Songs () {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        List <Song> result = dao.getTop5Songs();
        ServerContextHolder.clear();
        return result;
    }


    public List <Integer> getLastYearConnections () {
        Calendar calendar = Calendar.getInstance();
        List<Integer> results = new ArrayList<>();
        Date dateIterator = getLastYearFirstDay();
        do {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        } while (!changeOfYear(dateIterator));
        return results;
    }


    public List<Integer> getLastMonthConnections () {
        Calendar calendar = Calendar.getInstance();
        Date dateIterator = getLastMonthFirstDay();
        List<Integer> results = new ArrayList<>();
        do {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        } while (!changeOfMonth(dateIterator));
        return results;
    }



    public List <Integer> getLastWeekConnections () {
        Date dateIterator = getLastWeekMonday();
        List<Integer> results = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        }
        return results;
    }





    //Private Methods (used only in internal methods)

    //It returns the number of connections that there were in different dates
    private int getDayConnection (Date date) {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        int result;
        try {
            dao.CheckDateExists(date);
            result = dao.getDayConnection(date);
        } catch (BBDDException e) {
            result = 0;
        }
        finally {
            ServerContextHolder.clear();
        }
        return result;
    }


    private Date getLastWeekMonday () {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.add(Calendar.WEEK_OF_YEAR, -1);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        String convert = dt1.format(c.getTime());
        try {
            return dt1.parse(convert);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }



    private Date getLastMonthFirstDay () {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH,1);
        c.add(Calendar.MONTH,-1);
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        String convert = dt1.format(c.getTime());
        try {
            return dt1.parse(convert);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Date getLastYearFirstDay () {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,1);
        calendar.add(Calendar.YEAR,1);
        return calendar.getTime();
    }


    private boolean changeOfMonth (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    //This method says if a change of year have been produced or not
    private boolean changeOfYear (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR) == 1;
    }

    private Date incrementDay (Date dateToIncr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToIncr);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }



    //Getters and setters
    public DAOServer getDao() {
        return dao;
    }

    public void setDao(DAOServer dao) {
        this.dao = dao;
    }
}
