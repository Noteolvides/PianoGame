package Server.Controller.BBDD.DAOBBDD;



import Server.Controller.BBDD.Resources.BBDDException;
import Model.Song;
import Model.User;
import Model.Syst;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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


@Repository
public class DAOServer extends HibernateDaoSupport {


    @Autowired
    public DAOServer (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

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


    @Transactional (readOnly = true)
    public User getUser (String username) {
        List list = getHibernateTemplate().find("FROM " + User.class.getName() + " AS u WHERE u.nameUser = '" + username +"'");
        return (User) list.get(0);
    }


    @Transactional
    public void deleteUserByObject (User user) {
        getHibernateTemplate().delete(user);
    }

    @Transactional
    public void deleteUser (final String username) {
        List list = getHibernateTemplate().find("SELECT u FROM " + User.class.getName()+ " AS u WHERE u.nameUser = '"+ username+"'" );
        getHibernateTemplate().delete((User) list.get(0));
    }




    @Transactional
    public void insertUserTable (String username, String password, String userCode, String email) {
        User newUser = new User(username,userCode,password,email);
        getHibernateTemplate().save(newUser);
    }

    @Transactional
    public void insertUserTable (User user) {
        getHibernateTemplate().save(user);
    }


    @Transactional (readOnly = true)
    public void checkSongExistence (final String songName, final String author) throws BBDDException{
        List list = getHibernateTemplate().find("SELECT COUNT(*) FROM " + Song.class.getName() + " AS s WHERE s.title = '" + songName + "' AND (s.author = '" + author + "' OR s.syst = '" + author+ "'");
        Boolean b = ((Long) list.get(0) == 0);
        if (!b.booleanValue()) {
            throw new BBDDException();
        }
    }

    @Transactional
    public void insertSong (Song song) throws BBDDException {
        if (song != null) {
            getHibernateTemplate().save(song);
        }
        else {
            throw new BBDDException();
        }
    }

    @Transactional
    public void deleteSong (final String songName) {
        //TODO: Borrar referencias con amigos
        List list  = getHibernateTemplate().find("SELECT s FROM " + Song.class.getName() + " AS s WHERE s.title = '" + songName + "'");
        getHibernateTemplate().delete((Song) list.get(0));
    }

    @Transactional (readOnly = true)
    public List <Song> getAllTheSongs () {
        List query = getHibernateTemplate().find("SELECT Model.Song FROM Model.Song");
        List <Song> resultat = new ArrayList<Song>();
        for (int i = 0; i < query.size(); i++) {
            resultat.add((Song)query.get(i));
        }
        return resultat;
    }

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

    @Transactional(readOnly = true)
    public int getDayConnection (String dateToSearch) {
        List list = getHibernateTemplate().find ("SELECT s.totalUsers FROM "+ Syst.class.getName() + " AS s WHERE s.date = '" + dateToSearch + "'");
        return (Integer) list.get(0);
    }

    @Transactional(readOnly = true)
    public void CheckDateExists (String dateToCheck) throws BBDDException {

        List list = getHibernateTemplate().find("SELECT COUNT(*) FROM "+ Syst.class.getName() +" AS s WHERE s.date = '" + dateToCheck + "'");
        if((Long)list.get(0) == 0) {
            throw new BBDDException();
        }
    }

    @Transactional
    public void addConnection () {
        List list = getHibernateTemplate().find("SELECT s FROM "+ Syst.class.getName()+ " AS s WHERE s.date = DATE(NOW())");
        Syst s = (Syst) list.get(0);
        s.setTotalUsers(s.getTotalUsers()+1);
        getHibernateTemplate().update(s);
    }

    @Transactional
    public User searchUser (final String username) {
        List list = getHibernateTemplate().find("SELECT u FROM " + User.class.getName() +" AS u WHERE u.nameUser = '" + username + "'");

        return (User) list.get(0);
    }



    @Transactional
    public void createSystemToActualDate () {
        Calendar c = Calendar.getInstance();
        Syst system = new Syst ("System",new Date(),1);
        getHibernateTemplate().save(system);
    }

    @Transactional
    public void checkDateExistenceInSystem () throws BBDDException{
        List list = getHibernateTemplate().find ("SELECT COUNT(*) FROM " + Syst.class.getName() +" AS s WHERE s.date = DATE(NOW())");

        if (((Long) list.get(0)) == 0) {
            throw new BBDDException();
        }
    }


    @Transactional
    public void updateUserTable (User userModified) {
        getHibernateTemplate().merge(userModified);
    }


    @Transactional (readOnly = true)
    public List<Song> getSomeoneSongs (final String username) {
        List list = getHibernateTemplate().find("FROM " + Song.class.getName() + " AS s WHERE s.author='" + username + "'");
        return (List<Song>) list;
    }

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

    @Transactional (readOnly = true)
    public List <User> getSomeoneFriends (final String usernameToGetFriends) {
        List list = getHibernateTemplate().find("SELECT u.following FROM " + User.class.getName() + " AS u WHERE u.nameUser = '" + usernameToGetFriends + "'");
        return (List <User>) list;

    }


    public void recyprocityFriendship () {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                session.createSQLQuery("CALL updateFriends()");
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
}