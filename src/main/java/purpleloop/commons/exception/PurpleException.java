package purpleloop.commons.exception;

/** Base Exception class for the library. */
public class PurpleException extends Exception {

    /** Serial tag. */
    private static final long serialVersionUID = 8738360096660976235L;

    /**
     * Creates an exception with a message.
     * 
     * @param message informative message
     */
    public PurpleException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and a cause.
     * 
     * @param message informative message
     * @param cause cause of the exception
     */
    public PurpleException(String message, Throwable cause) {
        super(message, cause);
    }

}
