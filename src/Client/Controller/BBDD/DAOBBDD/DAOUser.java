package Client.Controller.BBDD.DAOBBDD;

import Client.Controller.BBDD.Resources.BBDDException;
import Client.Model.Song;
import Client.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class DAOUser extends HibernateDaoSupport {


    @Autowired
    public DAOUser (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    //This method is responsible for checking the existence of a specific user within the database
    @Transactional (readOnly = true)
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
    public void checkExistenceUserDatabaseWithoutPassword (String username) throws Server.Controller.BBDD.Resources.BBDDException {
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
            throw new Server.Controller.BBDD.Resources.BBDDException();
        }
    }


    @Transactional
    public void insertUserTable (String username, String password, String photoPath, String email) {
        User newUser = new User(username,photoPath,password,email);
        getHibernateTemplate().save(newUser);
    }


    @Transactional
    public void updateUserTable (User userModified) {
        getHibernateTemplate().update(userModified);
    }

    @Transactional
    public void insertSong (Song song) throws BBDDException{
        if (song != null) {
            getHibernateTemplate().save(song);
        }
        else {
            throw new BBDDException();
        }
    }

    public List<Song> getSomeoneSongs (String username) {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT * FROM Song AS s WHERE s.author ='" + username +"'");
                List <Song> resultat = ((NativeQuery) query).list();
                return resultat;
            }
        });
    }

    public List<Song> getSystemSongs () {
        return (List<Song>) getHibernateTemplate().execute(new HibernateCallback<Object>(){
            public Object doInHibernate(Session session) throws HibernateException {
                Query query2 = session.createSQLQuery("SELECT * FROM Syst AS s");
                Query query = session.createSQLQuery("SELECT * FROM Song AS s WHERE s.creator ='" + ((NativeQuery) query2).list().get(0) +"'");
                List <Song> resultat = ((NativeQuery) query).list();
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
