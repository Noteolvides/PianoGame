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
                int numUsers = query.executeUpdate();
                if (numUsers ==  0) {
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


    //TODO: Conexion principal, mirar si se puede comprobar la contraseña
    //With this method we can now check the existence of the user in our mysql server
    @Transactional(readOnly = true)
    public void checkExistenceUserMysql (String username, String password) throws BBDDException {
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM mysql.user WHERE user = '" + username + "'");
                int i = query.executeUpdate();
                return (i == 0);
            }
        });
        if (!result) {
            throw new BBDDException();
        }
    }


    @Transactional
    public void addUserIntoMysql (String username, String password) {
        getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("CREATE USER '" + username + "' IDENTIFIED BY '" + password + "'");
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
                int i = query.executeUpdate();
                return i == 0;
            }
        });
        if (!b) {
            throw new BBDDException();
        }
    }

    @Transactional
    public void insertSong (Song song) {
        getHibernateTemplate().save(song);
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

}
