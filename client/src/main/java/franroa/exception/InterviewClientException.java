package franroa.exception;

public class InterviewClientException extends Exception {
    public InterviewClientException() {
    }

    public InterviewClientException(String s) {
        super(s);
    }

    public InterviewClientException(Throwable throwable) {
        super(throwable);
    }
}
