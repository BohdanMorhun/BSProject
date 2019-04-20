package controller.exception;

public class WrongInputDataException extends Exception {

    public WrongInputDataException() {
        super();
    }

    public WrongInputDataException(String message) {
        super(message);
    }

    public WrongInputDataException(Throwable cause) {
        super(cause);
    }

    public WrongInputDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
