package franroa.simplehttp;

public class SimpleResponseException extends RuntimeException {
    public SimpleResponseException(String message) {
        super(message);
    }

    public SimpleResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
