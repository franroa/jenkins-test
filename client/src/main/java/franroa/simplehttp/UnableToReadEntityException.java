package franroa.simplehttp;

public class UnableToReadEntityException extends RuntimeException {
    public UnableToReadEntityException(String message) {
        super(message);
    }

    public UnableToReadEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
