package Server.Controller.BBDD.ServiceBBDD;



import Server.Controller.BBDD.DAOBBDD.DAOServer;
import Server.Controller.BBDD.MultiConnection.AvaiableClients;
import Server.Controller.BBDD.MultiConnection.ServerContextHolder;
import Server.Controller.BBDD.Resources.BBDDException;
import Server.Controller.BBDD.Resources.FieldsNoValidException;
import Model.Song;
import Model.User;
import Model.Syst;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * This is the class that allows the user to connect to the database. It should be noted that it is the outermost
 * layer of the database that deals with combining different queries (whenever necessary) and / or treating and
 * reviewing the data
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
@Service
public class ServiceBBDDServer {
    @Autowired
    private DAOServer dao;



    //:::::::::::::::::::CommonMethods::::::::::::::::::::::::::

    /**
     * This is the method that allows us to delete a song by the administering user (that is, from the server)
     * @param nameOfTheSong Name of the song that we want to delete
     * @param author The author of the song, it should be noted that this field is necessary because the two songs can have the same name provided, always, by different authors
     * @throws BBDDException This exception occurs when the name of the song already exists in the same user
     */
    public void deleteSong (String nameOfTheSong, String author) throws BBDDException {
            ServerContextHolder.clear();
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkSongExistence(nameOfTheSong, author,false,false);
            dao.deleteSong (nameOfTheSong,author);
            ServerContextHolder.clear();
    }

    /**
     * This is the method that allows us to delete a user through its own object
     * @param user This is the object of the user that we want to remove from the database.
     * @throws Exception A FieldsNoValidException and a BBDDException can occur, either by passing a null user or because the user entered does not exist
     */
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

    /**
     * This method allows us to eliminate the user from the database using his username
     * @param username It is the name of the user that we want to delete
     * @throws Exception This method can throw a FieldsNoValidException or a BBDDException, in the first case because the user has entered an invalid name and in the second case because the user
     * that has been introduced to eliminate has not been found
     */
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
    public Syst getInstanceOfSystem () {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        Syst s = dao.getFirstSystem();
        ServerContextHolder.clear();
        return s;
    }



    /**
     * This is a method that can be called if there is a problem with the CEST Time in the database.
     * In principle, this method is obsolete, because now the communication is done through time in UTC always,
     * but you can resort to it in case there is a problem.
     */
    public void databaseInitialization () {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.databaseInitialization();
        ServerContextHolder.clear();
    }


    /**
     * This is a method that allows the system to create a user by passing all the necessary fields.
     * @param username The username of the new user
     * @param password The password of the new user
     * @param alphanumericCode This is the alphanumeric code of the user
     * @param email The email of the new user
     * @throws Exception It can produce a FieldsNoValidException or a BBDDException, in the first case
     * whenever we introduce some empty field or with spaces, and in the second if the name of the user
     * or email already exists
     */
    public void createUserFromSystem (String username, String password, String alphanumericCode, String email) throws Exception {

        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(username,true);
            dao.checkExistenceEmailDatabaseWithoutPassword(email,true);
            dao.insertUserTable(username, password, alphanumericCode, email);
            ServerContextHolder.clear();
        }
        else {
            ServerContextHolder.clear();
            throw new FieldsNoValidException();
        }
    }

    /**
     * This a method that allows the system to insert a song, by passing all her attributes
     * @param name This is the name of the song that we want to create
     * @param duration This is the duration of the song that we want to create
     * @param description This is the description of the new song
     * @param plays These are the total reproductions that a song has received
     * @param filePath This is the address of the MIDI file inside the server.
     * @param syst This is the instance of the system that created it
     * @throws Exception This method can throw an exception (FieldNoValidException or BBDDException) in case an invalid field is entered
     * (such as a space or an empty field) or if the song already exists in the database.
     */
    public void insertSongFromSystem (String name, int duration, String description, int plays, String filePath, Syst syst) throws Exception {
        Song song = new Song(name, duration,description,plays,filePath, syst);
        if (name.equals("") || name.contains(" ") || filePath.equals("") || filePath.contains(" ")) {
            ServerContextHolder.set(AvaiableClients.adminSmartPiano);
            dao.checkSongExistence(name,syst.getName(),true,true);
            dao.insertSong(song);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }




    /**
     * Method to obtain (in the server) all the songs regardless of whether they are private or public
     * @return Returns the list of all songs available on the platform
     */
    public List<Song> getListSongs () {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        List <Song> result = dao.getAllTheSongs();
        ServerContextHolder.clear();
        return result;
    }


    /**
     * This method is used to obtain the TOP 5 of the most played songs by users.
     * @return Returns the 5 most played songs of our platform
     */
    public List <Song> getTop5Songs () {
        ServerContextHolder.set(AvaiableClients.adminSmartPiano);
        List <Song> result = dao.getTop5Songs();
        ServerContextHolder.clear();
        return result;
    }

    /**
     * This method gets all the songs that were played every day of the last year.
     * @return It returns a list with all the songs that were reproduced during the 365/366 days of last year.
     */
    public List <Integer> getLastYear365Connections () {
        Calendar calendar = Calendar.getInstance();
        List<Integer> results = new ArrayList<Integer>();
        Date dateIterator = getLastYearFirstDay();
        do {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        } while (!changeOfYear(dateIterator));
        return results;
    }

    /**
     * This method deals with obtaining the total of reproductions that were during all the months of last year
     * @return It returns a list of all the songs that were played during the 12 months of last year.
     */
    public List <Integer> getLastYearConnections () {
        Calendar calendar = Calendar.getInstance();
        List<Integer> results = new ArrayList<Integer>();
        Date dateIterator = getLastYearFirstDay();
        int acumulate = getDayConnection(dateIterator);
        dateIterator = incrementDay(dateIterator);
        do {
            if (changeOfMonth(dateIterator)) {
                results.add(acumulate);
                acumulate = 0;
            }
            acumulate = acumulate + getDayConnection(dateIterator);
            dateIterator = incrementDay(dateIterator);
        } while (!changeOfYear(dateIterator));
        results.add(acumulate);
        return results;
    }

    /**
     * This method is responsible for obtaining all the connections that were made during the past month
     * @return Returns a list of all the reproductions that occurred during all the days of the last month
     */
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


    /**
     * This is the method that deals with obtaining all the connections that were made during the past week
     * @return Returns a list that contains all the connections that were made during the past week (sorted by days of the week).
     */
    public List <Integer> getLastWeekConnections () {
        Date dateIterator = getLastWeekMonday();
        List<Integer> results = new ArrayList<Integer>();
        for (int i = 1; i <= 7; i++) {
            results.add(getDayConnection(dateIterator));
            dateIterator = incrementDay(dateIterator);
        }
        return results;
    }

    //:::::::::::::::::::USER BBDD METHODS::::::::::::::::::::

    //Method to delete your user

    /**
     * This method allows the user to delete his own user.
     * @param username It is the name of the current user, that is, the one we want to eliminate
     * @throws BBDDException This exception occurs when the name entered by the user does not exist, and therefore the user can not be deleted.
     */
    public void deleteUser (String username) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.deleteUser(username);
        ServerContextHolder.clear();
    }




    /**
     * Method to update the information of a user, so when something changed in it, it is uptated in the database
     * @param user The instance of user with the new user information loaded
     * @throws BBDDException In this method, an exception occurs when the user's name or email does not exist and therefore the information can not be updated
     */
    public void updateInformationUser (User user) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkExistenceUserDatabaseWithoutPassword(user.getNameUser(),false);
        dao.checkExistenceEmailDatabaseWithoutPassword(user.getEmail(),false);
        dao.updateUserTable(user);
        //This method ensure the bidirectionality of friendship (so if anyone does not have bidirectionality this method puts it)
        dao.recyprocityFriendship();
        ServerContextHolder.clear();
    }

    /**
     * This method allows the user to update the information of a particular song (in most cases it is used to update the reproductions of a particular song)
     * @param song It's the instance of a song, with the new information uploaded
     * @throws BBDDException This exception occurs when the name of the song or author has been modified, then it is not updated
     */
    public void updateSong (Song song) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        if (song.getAuthor() != null) {
            dao.checkSongExistence(song.getTitle(), song.getAuthor().getNameUser(), false,false);
        }
        else {
            dao.checkSongExistence(song.getTitle(), song.getSystem().getName(), true,false);
        }
        dao.updateSong(song);
        ServerContextHolder.clear();
    }




    /**
     * This is the method to search if a user exists passing it a  username, if exists we return it.
     * @param usernameToSearch It's the name of the user that we want to search
     * @return It returns the instance of the user searched
     * @throws BBDDException This exception occurs when the searched user does not exist on the platform and therefore its instance can not be returned
     */
    public User searchUserByUsername (String usernameToSearch) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkExistenceUserDatabaseWithoutPassword(usernameToSearch,false);
        User user = dao.searchUser(usernameToSearch);
        ServerContextHolder.clear();
        return user;
    }

    /**
     *  This is the method to search if a user exists passing it the alphanumeric code, if exists we return it.
     *  This method is basically used to acquire the user that the user wants to add
     * @param code It's the alphanumeric code of the user
     * @param username It's userName
     * @return It returns the instance of the user searched.
     * @throws BBDDException If the user does not exist, an exception is returned, because the user's instance can not be returned.
     */
    public User searchUserByCode (String code, String username) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkExistenceCode(code);
        User u = dao.getUserByCode(code);
        if (!u.getNameUser().equals(username)) {
            User user = dao.getUserByCode(code);
            ServerContextHolder.clear();
            return user;
        }
        else {
            ServerContextHolder.clear();
            throw new BBDDException();
        }
    }


    //Method to increase one connection to the syst

    /**
     * This method is the responsible to increase one connection to the system. Therefore, when a user is granted,
     * this method will have to be called to increase the connections in one
     */
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


    /**
     * This method insert a user (a crossing of the instance) in the user table, to make it avaiable
     * @param user This is the instance of the new user created that we want to add in the database
     * @throws BBDDException This exception is thrown when the username/email already exists in the database
     */
    public void createUserFromNoUser (User user) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.checkExistenceUserDatabaseWithoutPassword(user.getNameUser(),true);
        dao.checkExistenceEmailDatabaseWithoutPassword(user.getEmail(),true);
        //We generate the userCode, correspondent to the user
        userCodeCalculate (user);
        dao.insertUserTable(user);
        ServerContextHolder.clear();
    }

    /**
     * This method insert a user (a crossing of the different attributes) in the user table, to make it avaible
     * @param username This is the username of the new user
     * @param password This is the password of the new user
     * @param email This is the email taht the user puts on the new user
     * @throws Exception This exception is thrown when the username/email already exists in the database
     */
    public void createUserFromNoUser (String username, String password,String email) throws Exception {
        if (!(username.equals("") || username.contains(" ") || password.contains(" ") || password.equals(""))) {
            ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
            dao.checkExistenceUserDatabaseWithoutPassword(username,true);
            dao.checkExistenceEmailDatabaseWithoutPassword(email,true);
            //We generate the userCode, correspondent to the user
            User user = new User (username,password,email);
            userCodeCalculate(user);
            dao.insertUserTable(user);
            ServerContextHolder.clear();
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    //If the user wants to change his information (list of friends updated...), this method doesn't controll the change of username

    /**
     * This is called when we want to update the information of a user with the user register mysql account
     * @param user The instance of the user with the information updated
     */
    public void modifyInformationUser (User user){
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.updateUserTable(user);
        ServerContextHolder.clear();
    }


    /**
     * This method is called when we want to get an instance of a user with his username and password
     * @param username The username of the user that we want to get the instance
     * @param password The password of the user thtat we want to get the instance
     * @return It returns the instance of the username/password putted, when all goes right
     * @throws BBDDException It throws an exception when the user and the password do not match, or the username doesn't exist
     */
    public User getInstanceOfAUserByName (String username, String password) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.checkExistenceUserDatabase(username,password,false);
        User user = dao.getUser(username);
        ServerContextHolder.clear();
        return user;
    }


    /**
     * This is the method allows us to obtain the instance of a user by his email
     * @param email Email of the user that we want to get the instance
     * @param password Password of the user that we want to get the instance
     * @return We return the complete instance of the user passed
     * @throws BBDDException This method throws an exception when the email and the password doesn't match
     */
    public User getInstanceOfAUserByEmail (String email, String password) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.noUserSmartPiano);
        dao.checkExistenceEmailDatabase(email,password,false);
        User user = dao.getUserByEmail(email);
        ServerContextHolder.clear();
        return user;
    }


    /**
     * Method called when the user wants to add a song (the id is assigned by the database because it's serial) with the user registered mysql user
     * @param name This is the name of the song that we want to add to the platform
     * @param duration Duration of the song (Optional, it's not mandatory)
     * @param description This is the description that we want to add in our song
     * @param author This is the user that have created the song
     * @param plays This is the number of plays that a song have suffered
     * @param filePath This is the path of the song in the server (where the server have the midi)
     * @param privacity This boolean indicates if the songs it's private (true) or not (false)
     * @throws Exception This Exception it's thrown when: 1)Some of the fields contain spaces or are null 2) The songs of the user already exists
     */
    public void insertSongFromUser (String name, int duration, String description, User author, int plays, String filePath, boolean privacity) throws Exception {
        Song song  = new Song(name,duration,description,plays,filePath,privacity,author);
        if (duration == 0 || author == null || filePath.equals("") || filePath.contains(" ")) {
            throw new FieldsNoValidException();
        }
        else {
            dao.checkSongExistence(name,author.getNameUser(),false,true);
            dao.insertSong (song);
        }
    }


    /**
     * This method allows the user to insert a new song into the database
     * @param song Song that the user wants to insert
     * @throws BBDDException This exception is thrown when the song already exists in the user songs
     */
    public void insertSongFromUser(Song song) throws BBDDException {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        dao.checkSongExistence(song.getTitle(),song.getAuthor().getNameUser(),false,true);
        dao.insertSong(song);
        ServerContextHolder.clear();
    }


    /**
     * It's a method to obtain an specific song from the user
     * @param userInterested User that wants to obtain a concrete song
     * @param username The username of the song author that we want to search
     * @param songName The name of the song that we want to search
     * @return We return the instance of the song that match with the parameters, if all goes good.
     * @throws Exception This exception is thrown when the song of the user passed does not exists or when the song parameter is
     * null or with an space
     */
    public Song getConcreteSongUser (String userInterested, String username, String songName) throws Exception {
        ServerContextHolder.set(AvaiableClients.UserRegistered);
        List <Song> songsThatUserCanAccess = null;
        songsThatUserCanAccess = getSongsUser(userInterested);
        int i = 0;
        boolean found = false;
        while (!found && i < songsThatUserCanAccess.size()) {
            if (songName.equals(songsThatUserCanAccess.get(i).getTitle())) {
                found = true;
            }
            i++;
        }
        if (found) {
            List<Song> song = dao.searchConcreteSong(songName);
            int h = 0;
            while ((!(song.get(h).getAuthor() != null && ((song.get(h).getAuthor().getNameUser()).equals(username))) && (!(song.get(h).getAuthor() == null && !song.get(h).getSystem().getName().equals(username))))) {
                h++;
            }
            ServerContextHolder.clear();
            return song.get(h);
        } else {
            ServerContextHolder.clear();
            throw new BBDDException();
        }
    }





    /**
     * This method allows the server to obtain all the songs of a concrete user
     * @param username The username that we want to obtain all the songs
     * @return We return the list of the different songs that the user have created
     * @throws Exception This exception is thrown when the username contains an space or when it's null, or when the user does not exists
     */
    @Transactional
    public List<Song> getSongsUser (String username) throws Exception{
        if (!(username.equals("") || username.contains(" "))) {
            ServerContextHolder.set(AvaiableClients.UserRegistered);
            dao.checkExistenceUserDatabaseWithoutPassword(username,false);
            List <Song> songs = null;
            List <Song> songsSystem  = dao.getSystemSongs();
            songs = dao.getSomeoneSongs(username);
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
            boolean trobat=false;
            List <Song> publicSongs = dao.searchPublicSongs();
            for (int j = 0; j < publicSongs.size();j++) {
                trobat = false;
                for (int h = 0; h < songs.size();h++) {
                    if (publicSongs.get(j).getSongID() == songs.get(h).getSongID()) {
                        trobat = true;
                        break;
                    }
                }
                if (!trobat) {
                    songs.add(publicSongs.get(j));
                }
            }
            songs.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return ((Integer)o2.getPlays()).compareTo(o1.getPlays());
                }
            });

            int i = 0;
            //Code to evit the recursivity (the infinit bucle), because the exclude does not work in this case
            ArrayList <Song> songsAux = new ArrayList<>();
            while (i < songs.size()) {
                if (songs.get(i).getAuthor() != null) {
                    //songs.get(i).getAuthor().setSongs(null);
                    //songs.get(i).getAuthor().setFollowing(null);
                    User userAdapted = songs.get(i).getAuthor();
                    songsAux.add(new Song(songs.get(i).getTitle(), songs.get(i).getDuration(), songs.get(i).getDescription(),songs.get(i).getPlays(), songs.get(i).getFilePath(),songs.get(i).getPrivacity(),new User(userAdapted.getNameUser(),userAdapted.getPassword(), userAdapted.getUserCode(), userAdapted.getEmail())));
                }
                else {
                    //songs.get(i).getSystem().setSongs(null);
                    Syst syst = songs.get(i).getSystem();
                    songsAux.add(new Song(songs.get(i).getTitle(), songs.get(i).getDuration(), songs.get(i).getDescription(),songs.get(i).getPlays(), songs.get(i).getFilePath(),new Syst(syst.getName(),syst.getDate(),syst.getTotalUsers())));
                }
                i++;
            }
            ServerContextHolder.clear();
            return songs;
        }
        else {
            throw new FieldsNoValidException();
        }
    }

    /**
     * With this method you can check if two users are already friends
     * @param username1 Username of the first user that you want to check if it's friend
     * @param username2 Username of the user that you want to check the relation
     * @return We return a boolean that indicate if they are friends or not
     */
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

    /**
     * It returns the number of connections that we have in one concrete date
     * @param date We pass to the method the date that we want to check the connection
     * @return We return the number of connections that we have in the passed day
     */
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


    /**
     * Method to obtain the date of the last week's monday
     * @return It returns the monday of last week
     */
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


    /**
     * Method to obtain the date of the last month's first day
     * @return We return in a Date format the first day of the past month
     */
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

    /**
     * Method to obtain the first day of last year
     * @return In this case, we return (in date format) which was the first day in last year
     */
    private Date getLastYearFirstDay () {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR,1);
        calendar.add(Calendar.YEAR,-1);
        return calendar.getTime();
    }

    /**
     * It's a method that indicates if we are in the first day of a month
     * @param date Date that we want to check
     * @return We return true if the date is the first day of the month or false if not
     */
    private boolean changeOfMonth (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }



    /**
     * This method says if a change of year have been produced or not
     * @param date Date that we want to check the change of the year
     * @return We return true if the date is the first day of the year, and false if not
     */
    private boolean changeOfYear (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR) == 1;
    }

    /**
     * Method to increment one day to a concrete date
     * @param dateToIncr This is the date that we want to increase one day
     * @return We return the date putted incremented by one day
     */
    private Date incrementDay (Date dateToIncr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateToIncr);
        calendar.add(Calendar.DAY_OF_YEAR,1);
        return calendar.getTime();
    }

    /**
     * It's a method to calculate the alphanumeric code of the user and introduce it in the user object
     * @param user The user which we want to calculate his code
     */
    private void userCodeCalculate (User user) {
        long now = Instant.now().toEpochMilli()/1000;
        String conversion = String.valueOf(now);
        String strFinal = "";
        if (String.valueOf(now).length() < 8) {
            strFinal = String.valueOf(now).substring(0,String.valueOf(now).length()-1);
            while(strFinal.length() != 8) {
                strFinal = '0' + strFinal;
            }

        }
        else {
            strFinal = conversion.substring(conversion.length() - 8, conversion.length());
        }
        strFinal = strFinal + user.getNameUser().substring(0,1);
        user.setUserCode(strFinal);
    }


    //::::::::::::::::::::::::::::Getters and setters:::::::::::::::::::::::::::::::::::
    public DAOServer getDao() {
        return dao;
    }

    public void setDao(DAOServer dao) {
        this.dao = dao;
    }




}
