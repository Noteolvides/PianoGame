package Client.Controller.BBDD.MultiConnection;

// In this case we are in front of the class that deals with managing the connection topic, it has a Thread reserved
// to perform this communication with the database.
public class ClientContextHolder {
    //And why is Thread local? The reason is clear
    // and is that only this thread can access the ClientDatabase object, any thread can access it (In this way, in my opinion
    // we avoid that you can access from the same connection to the same database, which would generate error).
    private static ThreadLocal<AvaiableClients> CONTEXT = new ThreadLocal<AvaiableClients>();

    // This method serves to tell you, Hey connection, now I want you to settle with this particular client.
    public static void set(AvaiableClients clientDatabase) {
        if (clientDatabase == null) {
            System.out.println("The client of the database cannot be null!!");
        }
        else {
            CONTEXT.set(clientDatabase);
        }
    }


    //It obtains the current connection.
    public static AvaiableClients getAvaiableClients () {
        return CONTEXT.get();
    }


    //It tells the connection: I have already finished with this particular client.
    public static void clear() {
        CONTEXT.remove();
    }
}
