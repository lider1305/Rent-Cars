package by.pvt.exception;

/**
 * This class catch exceptions from service layer
 */
public class ServiceException extends Exception {
    private String message;

    public ServiceException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    public ServiceException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
