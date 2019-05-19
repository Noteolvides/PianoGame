package Server.Controller.BBDD.Resources;

/**
 * This is the class that controls the noon-desired introductions to the database, it is like a third layer to prevent the user from inserting
 * invalid fields (such as the user's name with spaces ...)
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
public class FieldsNoValidException extends Exception {
    /**
     * Basic constructor to generate the exception (without an own message, and the cause of the excpetion)
     */
    public FieldsNoValidException() { super(); }
    /**
     * It's the same method than the basic constructor but it allows us to pass an string to show the error
     * @param message Error message that we want to advert to the user of our program that the field is no valid
     */
    public FieldsNoValidException(String message) { super(message); }
    /**
     * It's a constructor that we can use in case that we want to show the cause and the message when there is a FieldNoValidException
     * @param message Error message that we want to advert to the user of our program that the field is no valid
     * @param cause The cause that have generated the introduction of the attribute no valid exception
     */
    public FieldsNoValidException(String message, Throwable cause) { super(message, cause); }

    /**
     * It's a constructor that we can use in case that we want to show only the cause of the exception, without a own error message
     * @param cause The cause that have generated  the introduction of the attribute no valid exception
     */
    public FieldsNoValidException(Throwable cause) { super(cause); }
}