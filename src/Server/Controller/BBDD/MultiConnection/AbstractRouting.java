package Server.Controller.BBDD.MultiConnection;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * This is the method that deals with controlling connections (knows which datasource to use at any moment ...).
 * For example, when you change the connection specified in the ContextHolder class, it takes care to establish
 * the new connection
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 *
 */
public class AbstractRouting extends AbstractRoutingDataSource {
    /**
     * Call the appropriate database based on the actual current key,
     * @return Then this class returns the different connections related with a datasource
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return ServerContextHolder.getAvaiableClients();
    }
}