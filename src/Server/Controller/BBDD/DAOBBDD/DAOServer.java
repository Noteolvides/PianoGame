package Server.Controller.BBDD.DAOBBDD;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DAOServer extends HibernateDaoSupport {
    @Autowired
    public DAOServer (SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    public void addUser () {

    }
}
