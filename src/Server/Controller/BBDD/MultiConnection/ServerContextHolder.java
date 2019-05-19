package Server.Controller.BBDD.MultiConnection;

/**
 * In this case we are in front of the class that deals with managing the connection topic, it has a Thread reserved
 * to perform this communication with the database.
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
public class ServerContextHolder {
    //And why is Thread local? The reason is clear and is that only this thread can access the ClientDatabase object, any thread can access it (In this way, in my opinion
    // we avoid that you can access from the same connection to the same database, which would generate error).
    private static ThreadLocal<AvaiableClients> CONTEXT = new ThreadLocal<AvaiableClients>();

    /**
     * This method allows the user to change the user of the database, in order to launch other types of queries.
     * So, This method serves to tell you, Hey connection, now I want you to settle with this particular client.
     * @param clientDatabase The client (that needs to be one of the enumeration), that I want to establish a connection
     */
    public static void set(AvaiableClients clientDatabase) {
        if (clientDatabase == null) {
            System.out.println("The client of the database cannot be null!!");
        }
        else {
            CONTEXT.set(clientDatabase);
        }
    }


    //It obtains the current connection.

    /**
     * Method to obtain the current connection with the database
     * @return The current connection in the database
     */
    public static AvaiableClients getAvaiableClients () {
        return CONTEXT.get();
    }



    /**
     * It's a method to communicate that I have finished the communication with a particular client of the database,
     * and that at the moment it is not necessary to mantain the connection.
     */
    public static void clear() {
        CONTEXT.remove();
    }
}
