package Client.Controller.BBDD.DAOBBDD;

import Client.Model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    //TODO: Conexion diferente (porque la base de datos es diferente)
    //This method is responsible for checking the existence of a specific user within the database
    @Transactional
    public boolean checkExistenceUserDatabase (String username, String password) {
        String query = "SELECT COUNT(*) FROM User AS u WHERE u.name =? AND u.password =?";
        Object [] queryParameters = {username,password};

        List<?> num = getHibernateTemplate().find (query, queryParameters);
        //TODO: Comprobar resultado, no me fio del String
        if (num.get(0).equals("0")) {
            return false;
        }
        else {
            return true;
        }
    }


    //TODO: Conexion principal, mirar si se puede comprobar la contraseña
    //With this method we can now check the existence of the user in our mysql server
    @Transactional
    public boolean checkExistenceUserMysql (String username, String password) {
        boolean result = (boolean) getHibernateTemplate().execute(new HibernateCallback<Object>() {
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = session.createSQLQuery("SELECT COUNT(*) FROM mysql.user WHERE user = '" + username + "'");
                int i = query.executeUpdate();
                return (i != 0);
            }
        });
        return result;
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



}
