package Server.Controller.BBDD.Resources;

/**
 * This is a class that will take care of informing the user of any type of error
 * that could generate errors in the database. That is, it is a class that guarantees
 * the reliability of all queries.
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
public class BBDDException extends Exception {
    /**
     * Basic constructor to generate the exception (without an own message, and the cause of the excpetion)
     */
    public BBDDException() { super(); }

    /**
     * It's the same method than the basic constructor but it allows us to pass an string to show the error
     * @param message Error message that we want to advert to the user of our program
     */
    public BBDDException(String message) { super(message); }

    /**
     * It's a constructor that we can use in case that we want to show the cause and the message when there is a BBDDException
     * @param message Error message that we want to advert to the user of our program
     * @param cause The cause that have generated the database exception
     */
    public BBDDException(String message, Throwable cause) { super(message, cause); }

    /**
     * It's a constructor that we can use in case that we want to show only the cause of the exception, without a own error message
     * @param cause The cause that have generated the database exception
     */
    public BBDDException(Throwable cause) { super(cause); }
}