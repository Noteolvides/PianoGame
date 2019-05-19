package Server.Controller.BBDD.DAOBBDD;



import Server.Controller.BBDD.Resources.BBDDException;
import Model.Song;
import Model.User;
import Model.Syst;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This is the last layer, which allows us to communicate directly with the database and
 * therefore is used exclusively by the ServiceBBDDServer.
 */
@Repository
public class DAOServer extends HibernateDaoSupport {


    /**
     * It's the method to get the hibernate's session (it is remarkable that this is done automatically thanks to the autowired)
     * @param sessionFactory Session factory that we want to inject to the DAO
     */
    @Autowired
    public DAOServer (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * It's the method to check the user existence in the database, but checking also if the password match with the username
     * @param username Username that we want to check the existence in the database
     * @param password Password that we want to check if it matches the username
     * @param existException This is a boolean is passing to the server the responsability to decide to throw an exception
     *                       when the user not exist or when the user already exist
     * @throws BBDDException Exception Of with the eror
     */
    @Transactional(readOnly = true)
    public void checkExistenceUserDatabase ( String username, String password, boolean existException) throws BBDDException {
        List list = getHibernateTemplate().find("SELECT COUNT(*) FROM "+ User.class.getName() +" AS u WHERE u.nameUser ='"+ username +"' AND u.password ='" + password + "'");
        if (existException) {
            if ((Long) list.get(0) != 0) {
                throw new BBDDException();
            }
        }
        else {
            if ((Long) list.get(0) == 0) {
                throw new BBDDException();
            }
        }
    }

    /**
     * This method is used to check the user existence in the database, only passing the username
     * @param username The username that we want to check the existence in the database
     * @param existException This is a boolean is passing to the server the responsability to decide to throw an exception
     *                       when the user not exist or when the user already exist
     *
     * @throws BBDDException This exception is thrown when the user exist (in case that existException is true), and when the user not exist (in case that existException is false)
     */
    @Transactional (readOnly = true)
    public void checkExistenceUserDatabaseWithoutPassword (String username, boolean existException) throws BBDDException{
        List list = getHibernateTemplate().find ("SELECT COUNT(*) FROM " + User.class.getName() + " AS u WHERE u.nameUser = '" + username + "'");

        if (existException) {
            if ((Long) list.get(0) != 0) {
                throw new BBDDException();
            }
        }
        else {
            if ((Long) list.get(0) == 0) {
                throw new BBDDException();
            }
        }
    }

    /**
     * This method check the user existence only using an email, without any password
     * @param email Email that we want to check his existence
     * @param existException This is a boolean is passing to the server the responsability to decide to throw an exception
     *                       when the user not exist or when the user already exist
     * @throws BBDDException This exception is thrown when the user exist (in case that existException is true), and when the user not exist (in case that existException is false)
     */
    @Transactional
    public void checkExistenceEmailDatabaseWithoutPassword (String email,boolean existException) throws BBDDException {
        List list = getHibernateTemplate().find ("SELECT COUNT(*) FROM " + User.class.getName() + " AS u WHERE u.email = '" + email + "'");
        if (existException) {
            if ((Long) list.get(0) != 0) {
                throw new BBDDException();
            }
        }
        else {
            if ((Long) list.get(0) == 0) {
                throw new BBDDException();
            }
        }
    }

    /**
     * This method check the user existence with the email and also checkes the match of the email with the password
     * @param email The email that we want to check the existence
     * @param password The password that we are going to check the match with the email
     * @param existException This is a boolean is passing to the server the responsability to decide to throw an exception
     *                       when the user not exist (or the password and the email don't match) or when the user already
     *                       exist
     * @throws BBDDException This exception is thrown when the user exist and the password match (in case that existException is true), and when the user not exist or the username and the password does not match (in case that existException is false)
     */
    @Transactional(readOnly = true)
    public void checkExistenceEmailDatabase (String email, String password, boolean existException) throws BBDDException {
        List list = getHibernateTemplate().find ("SELECT COUNT(*) FROM " + User.class.getName() + " AS u WHERE u.email = '" + email + "' AND u.password = '" + password + "'");
        if (existException) {
            if ((Long) list.get(0) != 0) {
                throw new BBDDException();
            }
        }
        else {
            if ((Long) list.get(0) == 0) {
                throw new BBDDException();
            }
        }
    }

    /**
     * This method returns the instance of a user by passing it the username
     * @param username The username that we want to get the instance
     * @return We return the specific instance of user associeated with the passed username
     */
    @Transactional (readOnly = true)
    public User getUser (String username) {
        List list = getHibernateTemplate().find("FROM " + User.class.getName() + " AS u WHERE u.nameUser = '" + username +"'");
        return (User) list.get(0);
    }

    /**
     * This method returns the instance of a user by passing it the email
     * @param email The email that we want to get the instance
     * @return We return the specific instance of the user associated with the passed email
     */
    @Transactional (readOnly = true)
    public User getUserByEmail (String email) {
        List list = getHibernateTemplate().find("FROM " + User.class.getName() + " AS u WHERE u.email = '" + email +"'");
        return (User) list.get(0);
    }

    /**
     * This method allows us to delete the user by passing the instance of the user
     * @param user Instance of the user that we want to delete
     */
    @Transactional
    public void deleteUserByObject (User user) {
        getHibernateTemplate().delete(user);
    }

    /**
     * This method allows us to delete the user by passing the username of the user
     * @param username Username of the user that we want to delete
     */
    @Transactional
    public void deleteUser (final String username) {
        List list = getHibernateTemplate().find("SELECT u FROM " + User.class.getName()+ " AS u WHERE u.nameUser = '"+ username+"'" );
        getHibernateTemplate().delete((User) list.get(0));
    }

    /**
     * This method allows us to check the existence of the alphanumeric code associated with a concrete user
     * @param codeToSearch This is the code that we want to check the existence
     * @throws BBDDException This exception is thrown when the code does not exist in the platform
     */
    @Transactional (readOnly = true)
    public void checkExistenceCode (String codeToSearch) throws BBDDException {
        List list = getHibernateTemplate().find("SELECT COUNT(*) FROM " + User.class.getName() + " AS u WHERE u.userCode = '" + codeToSearch + "'" );
        if ((Long) list.get(0) == 0) {
            throw new BBDDException();
        }
    }

    /**
     * This method returns the instance of a user passing only the alphanumeric code associated with a concrete user
     * @param codeToSearch The alphanumeric code that we want to obtain the user existence
     * @return We return the instance of the user assoaciated with the alphanumeric code
     */
    @Transactional(readOnly = true)
    public User getUserByCode (String codeToSearch)  {
        List list = getHibernateTemplate().find("FROM " + User.class.getName() + " AS u WHERE u.userCode = '" + codeToSearch + "'" );
        return (User) list.get(0);
    }


    /**
     * This method insert a user in the table, passing only some attributes
     * @param username The username of the user that we want to introduce
     * @param password The password of the user that we want to introduce
     * @param userCode The user alphanumeric code  of the user that we want to introduce
     * @param email The email of the user that we want to introduce
     */
    @Transactional
    public void insertUserTable (String username, String password, String userCode, String email) {
        User newUser = new User(username,userCode,password,email);
        getHibernateTemplate().save(newUser);
    }

    /**
     * This method have the responsability to insert a user in the user table
     * @param user The instance of the user that we want to introduce to the platform
     */
    @Transactional
    public void insertUserTable (User user) {
        getHibernateTemplate().save(user);
    }


    /**
     * This method is the responsible to check the existence of one concrete song on the plaftorm, passing the song and the author
     * @param songName The name of the song that we want to check the existence
     * @param author The name of the author that have he song entered
     * @param system We indicate to the method if the name of the author is a username or is the name of the system
     * @param existException The service have the responsability decide to throw an exception if the song exist (putting this boolean to true) or
     *                       if it does not exist
     * @throws BBDDException This exception is thrown when the song exist  (in case that existException is true), and when the song not exist (in case that existException is false)
     */
    @Transactional (readOnly = true)
    public void checkSongExistence (final String songName, final String author,boolean system,boolean existException) throws BBDDException{
        List list;
        if (!system) {
            list = getHibernateTemplate().find("SELECT COUNT(*) FROM " + Song.class.getName() + " AS s WHERE s.title = '"+ songName + "' AND (s.author.nameUser = '" + author + "')");
        }
        else {
            list = getHibernateTemplate().find("SELECT COUNT(*) FROM " + Song.class.getName() + " AS s WHERE s.title = '" + songName + "' AND (s.system.nameSyst = '" + author + "')");
        }
        Boolean b = ((Long) list.get(0) == 0);
        if (existException) {
            if (!b.booleanValue()) {
                throw new BBDDException();
            }
        }
        else {
            if (b.booleanValue()) {
                throw new BBDDException();
            }
        }
    }

    /**
     * This method allows us to insert one concrete song by passing the instance of it
     * @param song Instance song that we want to insert
     * @throws BBDDException If the song passed it's null, we throw a BBDDException
     */
    @Transactional
    public void insertSong (Song song) throws BBDDException {
        if (song != null) {
            getHibernateTemplate().save(song);
        }
        else {
            throw new BBDDException();
        }
    }

    /**
     * This method it's used to delete a song by passing the name of the song and the author of the song
     * @param songName This is the name of the song that we want to delete
     * @param author This is the name of the author of the song that we want to delete
     */
    @Transactional
    public void deleteSong (final String songName, String author) {
        //TODO: Borrar referencias con amigos
        List list  = getHibernateTemplate().find("SELECT s FROM " + Song.class.getName() + " AS s WHERE s.title = '" + songName + "' AND s.author.nameUser ='" + author+ "'");
        getHibernateTemplate().delete((Song) list.get(0));
    }

    /**
     * Mehtod to obtain all the songs that are avaiable on the database
     * @return We return the list of the songs of the platform
     */
    @Transactional (readOnly = true)
    public List <Song> getAllTheSongs () {
        List query = getHibernateTemplate().find("FROM " + Song.class.getName());
        List <Song> resultat = new ArrayList<Song>();
        for (int i = 0; i < query.size(); i++) {
            resultat.add((Song)query.get(i));
        }
        return resultat;
    }

    /**
     * Method to obtain the top 5 songs of the platform
     * @return We return a list of songs ordered by plays
     */
    @Transactional (readOnly = true)
    public List <Song> getTop5Songs () {
        List list = getHibernateTemplate().find("SELECT s FROM " + Song.class.getName() + " AS s ORDER BY (s.plays) DESC");

        int max = 5;
        List <Song> result = new ArrayList<Song>();
        if (list.size () < 5) {
            max = list.size();
        }
        for (int i  = 0; i < max; i++) {
            result.add((Song) list.get(i));
        }
        return result;
    }

    /**
     * Method to obtain a concrete date connections
     * @param dateToSearch Date that we want to know how many users have been connected
     * @return We return an int that indicates the user connections in the dateToSearch
     */
    @Transactional(readOnly = true)
    public int getDayConnection (String dateToSearch) {
        List list = getHibernateTemplate().find ("SELECT s.totalUsers FROM "+ Syst.class.getName() + " AS s WHERE s.date = '" + dateToSearch + "'");
        return (Integer) list.get(0);
    }

    /**
     * Method that checks if one date have connections
     * @param dateToCheck The concrete date that we want to check the number of connections
     * @throws BBDDException In case there is no row in the system with this specific date, the server is informed to create a specific one or manage it in some way
     */
    @Transactional(readOnly = true)
    public void CheckDateExists (String dateToCheck) throws BBDDException {

        List list = getHibernateTemplate().find("SELECT COUNT(*) FROM "+ Syst.class.getName() +" AS s WHERE s.date = '" + dateToCheck + "'");
        if((Long)list.get(0) == 0) {
            throw new BBDDException();
        }
    }

    /**
     * Method to add a new connection to the actual date
     */
    @Transactional
    public void addConnection () {
        List list = getHibernateTemplate().find("SELECT s FROM "+ Syst.class.getName()+ " AS s WHERE s.date = DATE(NOW())");
        Syst s = (Syst) list.get(0);
        s.setTotalUsers(s.getTotalUsers()+1);
        getHibernateTemplate().update(s);
    }

    /**
     * Method to search a user by his username, and return the instance of it.
     * @param username Username of the user that we want to obtain the instance
     * @return We return the instance of the user related with the username passed
     */
    @Transactional
    public User searchUser (final String username) {
        List list = getHibernateTemplate().find("SELECT u FROM " + User.class.getName() +" AS u WHERE u.nameUser = '" + username + "'");

        return (User) list.get(0);
    }


    /**
     * Method to create a new row with the actual date associated, and with 1 connection
     */
    @Transactional
    public void createSystemToActualDate () {
        Calendar c = Calendar.getInstance();
        Syst system = new Syst ("System",new Date(),1);
        getHibernateTemplate().save(system);
    }

    /**
     * This method check the existence of the actual date in our database
     * @throws BBDDException In case there is no row in the system with the actual date, the server is informed to create a new one or manage it in some way
     */
    @Transactional
    public void checkDateExistenceInSystem () throws BBDDException{
        List list = getHibernateTemplate().find ("SELECT COUNT(*) FROM " + Syst.class.getName() +" AS s WHERE s.date = DATE(NOW())");

        if (((Long) list.get(0)) == 0) {
            throw new BBDDException();
        }
    }


    /**
     * This method is the responsible to update the information of the user in the database
     * @param userModified We pass to the method the instance of the user with the modified information
     */
    @Transactional
    public void updateUserTable (User userModified) {
        getHibernateTemplate().merge(userModified);
    }


    /**
     * This method is the responsible to update the song information in the database
     * @param s We pass to the method the instance of the song with the modified information
     */
    @Transactional
    public void updateSong (Song s) {
        getHibernateTemplate().update(s);
    }

    /**
     * This method is the responsible to obtain the songs that one concrete user can access
     * @param username  We pass the username associated to the user that we want to obtain the songs that can access
     * @return It returns a list with all the songs that the user associated with the username can access
     */
    @Transactional (readOnly = true)
    public List<Song> getSomeoneSongs (final String username) {
        List list = getHibernateTemplate().find("FROM " + Song.class.getName() + " AS s LEFT JOIN FETCH s.author WHERE s.author='" + username + "'");
        return (List<Song>) list;
    }

    /**
     * Method that allows us to get all the songs that are created by the system
     * @return It returns a list of songs with all the songs that the system have created
     */
    @Transactional (readOnly = true)
    public List<Song> getSystemSongs () {
        List list = getHibernateTemplate().find ("SELECT s.nameSyst FROM "+ Syst.class.getName() +" AS s");
        if (list.size() != 0) {
            List <?> list2 = getHibernateTemplate().find("SELECT s2 FROM " + Song.class.getName() + " AS s2 WHERE s2.system <> null");
            return (List<Song>) list2;
        }
        else {
            return new ArrayList<Song>();
        }
    }


    /**
     * Method that allows us to get the friends of one concrete user
     * @param usernameToGetFriends This is the username of the user that we want to search his friends
     * @return We return a list with all the users that are friends of the specified user
     */
    @Transactional (readOnly = true)
    public List <User> getSomeoneFriends (final String usernameToGetFriends) {
        List list = getHibernateTemplate().find("SELECT u.following FROM " + User.class.getName() + " AS u WHERE u.nameUser = '" + usernameToGetFriends + "'");
        return (List <User>) list;

    }

    /**
     * Method that allows us to make the relation with the other users bidireccional
     */
    @Transactional
    public void recyprocityFriendship () {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("CALL updateFriends()");
                 query.executeUpdate();
                return null;
            }
        });

    }

    @Transactional (readOnly = true)
    public List<Song> searchConcreteSong (final String songName) {
        List list = getHibernateTemplate().find("FROM " + Song.class.getName() + " AS s WHERE s.title ='" + songName + "'");
        return (List<Song>) list;
    }
    public List<Song> searchConcreteSongWithId (final int id) {
        List list = getHibernateTemplate().find("FROM " + Song.class.getName() + " AS s WHERE s.id ='" + id + "'");
        return (List<Song>) list;
    }

    @Transactional (readOnly = true)
    public List<Song> searchPublicSongs () {
        List list = getHibernateTemplate().find("FROM " + Song.class.getName() + " AS s LEFT JOIN FETCH s.author WHERE s.privacity = FALSE ");
        return (List<Song>) list;
    }

    @Transactional
    public void databaseInitialization () {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("CALL databaseInitialization()");
                query.executeUpdate();
                return null;
            }
        });
    }

    @Transactional
    public Syst getFirstSystem () {
        List list = getHibernateTemplate().find ("FROM " + Syst.class.getName() + " AS s WHERE s.ID = " + 1);
        return (Syst) list.get(0);
    }
}