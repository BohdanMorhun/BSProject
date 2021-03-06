package controller.exception;

public class ServiceLayerException extends Exception {

    public ServiceLayerException() {
        super();
    }

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
