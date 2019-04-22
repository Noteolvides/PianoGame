package Server.Controller.BBDD.Resources;

public class FieldsNoValidException extends Exception {
    public FieldsNoValidException() { super(); }
    public FieldsNoValidException(String message) { super(message); }
    public FieldsNoValidException(String message, Throwable cause) { super(message, cause); }
    public FieldsNoValidException(Throwable cause) { super(cause); }
}