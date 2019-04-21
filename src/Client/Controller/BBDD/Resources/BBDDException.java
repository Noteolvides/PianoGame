package Client.Controller.BBDD.Resources;

public class BBDDException extends Exception {
    public BBDDException() { super(); }
    public BBDDException(String message) { super(message); }
    public BBDDException(String message, Throwable cause) { super(message, cause); }
    public BBDDException(Throwable cause) { super(cause); }
}