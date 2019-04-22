package Server.Controller.BBDD.MultiConnection;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


// This is the method that deals with controlling connections (knows which datasource to use at any moment ...). For example,
// I'm pretty sure that when you change the connection specified in the ContextHolder class, it takes care to
// establish the new connection
public class AbstractRouting extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return ClientContextHolder.getAvaiableClients();
    }
}