package Server.Controller.BBDD.ServiceBBDD;



import Server.Controller.BBDD.DAOBBDD.DAOServer;
import Server.Controller.BBDD.MultiConnection.AvaiableClients;
import Server.Controller.BBDD.MultiConnection.ServerContextHolder;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.Resources.FieldsNoValidException;
import Model.Song;
import Model.User;
import Model.Syst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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







    //TODO: Don't controll strings

    //:::::::::::::::::::CommonMethods::::::::::::::::::::::::::
    public void deleteSong (String nameOfTheSong) throws FieldsNoValidException {
        try {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkSongExistence(nameOfTheSong);
            ServerContextHolder.clear();

        } catch (BBDDException e) {
            dao.deleteSong (nameOfTheSong);
            ServerContextHolder.clear();
        }
    }
    public void deleteUserByObject (User user) throws Exception {
        if (user != null) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(user.getNameUser(),false);
            dao.deleteUserByObject(user);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    public void deleteUserByName (String username) throws Exception{
        if (!(username.equals("") || username.contains(" "))) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(username,false);
            dao.deleteUser(username);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //:::::::::::::::::::Server BBDD Methods:::::::::::::::::::::::::::::::
    public void createUserFromSystem (String username, String password, String photoPath, String email) throws Exception {

        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabase(username, password,true);
            dao.insertUserTable(username, password, photoPath, email);
            ServerContextHolder.clear();
        }
        else {
            ServerContextHolder.clear();
            throw new FieldsNoValidException();
        }
    }


    public void insertSongFromSystem (String name, int duration, String description, int plays, String filePath, Syst syst) throws Exception {
        Song song = new Song(name, duration,description,plays,filePath, syst);
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
        List<Integer> results = new ArrayList<Integer>();
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
        List<Integer> results = new ArrayList<Integer>();
        do {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        } while (!changeOfMonth(dateIterator));
        return results;
    }



    public List <Integer> getLastWeekConnections () {
        Date dateIterator = getLastWeekMonday();
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i < 7; i++) {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        }
        return results;
    }

    //:::::::::::::::::::USER BBDD METHODS::::::::::::::::::::

    //Method to update the information of a user (the username mopdify is not valid in not valid in this method)
    public void updateInformationUser (User user) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkExistenceUserDatabaseWithoutPassword(user.getNameUser(),false);
        dao.updateUserTable(user);
        //this metodh is useles
        dao.recyprocityFriendship();
        ServerContextHolder.clear();
    }




    //This is the method to search if a user exists, if exists we return it.
    public User searchUser (String usernameToSearch) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkExistenceUserDatabaseWithoutPassword(usernameToSearch,false);
        User user = dao.searchUser(usernameToSearch);
        ServerContextHolder.clear();
        return user;
    }


    //Method to increase one connection to the syst
    public void addConnection () {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        try {
            dao.checkDateExistenceInSystem ();
            dao.addConnection();
        } catch (BBDDException e) {
            dao.createSystemToActualDate ();
        }
        finally {
            ServerContextHolder.clear();
        }
    }

    public void createUserFromNoUser (User user) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.checkExistenceUserDatabaseWithoutPassword(user.getNameUser(),true);
        dao.insertUserTable(user);
        ServerContextHolder.clear();
    }


    public void createUserFromNoUser (String username, String password, String photoPath, String email) throws Exception {
        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(username,true);
            dao.insertUserTable(username, password, photoPath, email);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //If the user wants to change his information (list of friends updated...), this method doesn't controll the change of username
    public void modifyInformationUser (User user){
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.updateUserTable(user);
        ServerContextHolder.clear();
    }


    public User getInstanceOfAUser (String username, String password) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.checkExistenceUserDatabase(username,password,false);
        User user = dao.getUser(username);
        ServerContextHolder.clear();
        return user;
    }


    //If the user wants to add a song (the id is assigned by the database because it's serial
    public void insertSongFromUser (String name, int duration, String description, User author, int plays, String filePath) throws Exception {
        Song song  = new Song(name,duration,description,plays,filePath,author);
        if (duration == 0 || author == null || filePath.equals("") || filePath.contains(" ")) {
            throw new FieldsNoValidException();
        }
        else {
            dao.checkSongExistence(name);
            dao.insertSong (song);
        }
    }

    public void insertSongFromUser(Song song) throws BBDDException {
        dao.checkSongExistence(song.getTitle());
        dao.insertSong(song);
    }


    public Song getConcreteSongUser (String username, String songName) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        List <Song> songsThatUserCanAccess = null;
        try {
            songsThatUserCanAccess = getSongsUser(username);
        } catch (Exception e) {
            ServerContextHolder.clear();
            throw new BBDDException();
        }
        int i  = 0;
        boolean found = false;
        while (!found && i < songsThatUserCanAccess.size()) {
            if (songName.equals(songsThatUserCanAccess.get(i).getTitle())) {
                found = true;
            }
            i++;
        }
        if (found) {
            List <Song> song = dao.searchConcreteSong(songName);
            ServerContextHolder.clear();
            return song.get(0);
        }
        else {
            ServerContextHolder.clear();
            throw new BBDDException();
        }
    }

    public List<Song> getSongsUser (String username) throws Exception{
        if (!(username.equals("") || username.contains(" "))) {
            ServerContextHolder.set(AvaiableClients.UserRegistered);
            dao.checkExistenceUserDatabaseWithoutPassword(username,false);
            List <Song> songs = new ArrayList<Song>();
            List <Song> songsSystem  = dao.getSystemSongs();
            for (int i = 0; i < songsSystem.size();i++) {
                songs.add(songsSystem.get(i));
            }
            List <User> friendsUser = dao.getSomeoneFriends(username);
            List <String> friends = new ArrayList<String>();
            for (int y = 0; y < friendsUser.size(); y++) {
                friends.add(friendsUser.get(y).getNameUser());
            }
            for (int j = 0; j < friends.size(); j++) {
                List <Song> songsFriend = dao.getSomeoneSongs(friends.get(j));
                for (int u = 0; u < songsFriend.size();u++) {
                    songs.add(songsFriend.get(u));
                }
            }
            ServerContextHolder.clear();
            return songs;
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //With this method you can check if two users are already friends
    public boolean checkUserRelationship (String username1, String username2) {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        List <User> friendsUser = dao.getSomeoneFriends(username1);
        List <String> friends = new ArrayList<String>();
        for (int u = 0; u < friendsUser.size(); u++) {
            friends.add(friendsUser.get(u).getNameUser());
        }
        if (friends.contains(username2)) {
            return true;
        }
        ServerContextHolder.clear();
        return false;
    }

    //:::::::::::::::::::::::::::::::::Private Methods (used only in internal methods):::::::::::::::::

    //It returns the number of connections that there were in different dates
    private int getDayConnection (Date date) {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        int result;
        try {
            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
            String convert = dt1.format(date);


            dao.CheckDateExists(convert);


            result = dao.getDayConnection(convert);

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



    //::::::::::::::::::::::::::::Getters and setters:::::::::::::::::::::::::::::::::::
    public DAOServer getDao() {
        return dao;
    }

    public void setDao(DAOServer dao) {
        this.dao = dao;
    }




}
