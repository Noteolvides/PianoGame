package Server.Controller.BBDD.DAOBBDD;

import Server.Model.Song;
import Server.Model.User;
import Server.Controller.BBDD.Resources.BBDDException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DAOServer extends HibernateDaoSupport {
    @Autowired
    public DAOServer (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Transactional(readOnly = true)
    public void checkExistenceUserDatabase (String username, String password) throws BBDDException {
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM User AS u WHERE u.name ='" + username +"' AND u.password ='" + password + "'");
                List <Integer> resultat = ((NativeQuery) query).list();
                if (resultat.get(0) ==  0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        if (!result) {
            throw new BBDDException();
        }
    }


    @Transactional
    public void checkExistenceUserDatabaseWithoutPassword (String username) throws BBDDException{
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM User AS u WHERE u.name ='" + username +"'");
                List <Integer> resultat = ((NativeQuery) query).list();
                if (resultat.get(0) ==  0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        });
        if (!result) {
            throw new BBDDException();
        }
    }





    @Transactional
    public void deleteUserByObject (User user) {
        getHibernateTemplate().delete(user);
    }

    @Transactional
    public void deleteUser (String username) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("DELETE FROM User AS u WHERE u.name = '" + username + "'");
                query.executeUpdate();
                return null;
            }
        });
    }




    @Transactional
    public void insertUserTable (String username, String password, String photoPath, String email) {
        User newUser = new User(username,photoPath,password,email);
        getHibernateTemplate().save(newUser);
    }

    @Transactional (readOnly = true)
    public void checkSongExistence (String songName) throws BBDDException{
        boolean b = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM Song AS s WHERE s.name = '" + songName + "'");
                List <Integer> resultat = ((NativeQuery) query).list();
                return resultat.get(0) == 0;
            }
        });
        if (!b) {
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
    public void deleteSong (String songName) {
        Song songToDelete = (Song) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT * FROM Song AS s WHERE s.name = '" + songName + "'");
                List<Song> result = ((NativeQuery) query).list();
                return  result.get(0);
            }
        });
        getHibernateTemplate().delete(songToDelete);
    }

    @Transactional (readOnly = true)
    public List <Song> getAllTheSongs () {
        List query = getHibernateTemplate().find("SELECT Server.Model.Song FROM Server.Model.Song");
        List <Song> resultat = new ArrayList<>();
        for (int i = 0; i < query.size(); i++) {
            resultat.add((Song)query.get(i));
        }
        return resultat;
    }

    @Transactional (readOnly = true)
    public List <Song> getTop5Songs () {
        List <Song> resultat = (List<Song>) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT * FROM Song AS s ORDER BY (s.plays) DESC LIMIT 5");
                List <Song> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
        return resultat;
    }

    @Transactional(readOnly = true)
    public int getDayConnection (Date dateToSearch) {
        int result = (int) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT * FROM Syst AS s WHERE s.date =" + dateToSearch);
                List <Song> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
        return result;
    }

    @Transactional(readOnly = true)
    public void CheckDateExists (Date dateToCheck) throws BBDDException {
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM Syst AS s WHERE s.date =" + dateToCheck);
                List <Integer> resultat = ((NativeQuery) query).list();
                return resultat.get(0) == 0;
            }
        });
        if(result) {
            throw new BBDDException();
        }
    }

    @Transactional
    public void addConnection () {
        int result = (int) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT TotalOfUser FROM Syst AS s WHERE s.date = DATE(NOW())");
                List <Integer> resultat = ((NativeQuery) query).list();
                return resultat.get(0);
            }
        });
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("UPDATE Syst SET TotalOfUsers ="+ result + " WHERE s.date = DATE(NOW())");
                return null;
            }
        });
    }

    @Transactional
    public void createSystemToActualDate () {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("INSERT INTO Syst VALUES ('System',NOW(),1)");
                query.executeUpdate();
                return null;
            }
        });
    }

    @Transactional
    public void checkDateExistenceInSystem () throws BBDDException{
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM System AS s WHERE s.date = DATE(NOW())");
                List <Integer> resultat = ((NativeQuery) query).list();
                return resultat.get(0) == 0;
            }
        });
        if (result) {
            throw new BBDDException();
        }
    }


    @Transactional
    public void updateUserTable (User userModified) {
        getHibernateTemplate().update(userModified);
    }



    public List<Song> getSomeoneSongs (String username) {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT * FROM Song AS s WHERE s.author ='" + username +"'");
                List <Client.Model.Song> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
    }

    public List<Song> getSystemSongs () {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query2 = session.createSQLQuery("SELECT * FROM Syst AS s");
                Query query = session.createSQLQuery("SELECT * FROM Song AS s WHERE s.creator ='" + ((NativeQuery) query2).list().get(0) +"'");
                List <Client.Model.Song> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
    }

    public List <String> getSomeoneFriends (String usernameToGetFriends) {
        return (List<String>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT f.Name2 FROM Friendship AS f WHERE (f.Name1 ='" + usernameToGetFriends + "') UNION  SELECT f.Name1 FROM Friendship AS f WHERE (f.Name2 ='" + usernameToGetFriends +"'");
                List <String> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
    }

}
