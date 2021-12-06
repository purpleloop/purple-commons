package io.github.purpleloop.commons.exception;

/** Mathematical exception, that can be used to handle various errors : zero divide, undefined, ... */
public class MathematicalException extends PurpleException {

    /** Serial tag. */
    private static final long serialVersionUID = 8738360096660976235L;

    /**
     * Creates an exception with a message.
     * 
     * @param message informative message
     */
    public MathematicalException(String message) {
        super(message);
    }

    /**
     * Creates an exception with a message and a cause.
     * 
     * @param message informative message
     * @param cause cause of the exception
     */
    public MathematicalException(String message, Throwable cause) {
        super(message, cause);
    }    
    
}
