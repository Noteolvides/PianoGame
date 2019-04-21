package Client.Controller.BBDD.DAOBBDD;

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

@Repository
public class DAOUser extends HibernateDaoSupport {


    @Autowired
    public DAOUser (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }


    //This method is responsible for checking the existence of a specific user within the database
    @Transactional
    public boolean checkExistenceUser (String username, String password) {
        String query = "SELECT COUNT(*) FROM User AS u WHERE u.name =? AND u.password =?";
        Object [] queryParameters = {username,password};
        try {
            int num = getHibernateTemplate().find (query, queryParameters);
            if (num == 0) {
                return false;
            }
            else {
                return true;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }




}
