package io.github.purpleloop.commons.lang;

/**
 * This interface is useful when controlling a thread.
 * 
 * <p>
 * The thread owner, for instance, registers as a ThreadObserver and can be
 * notified when the thread requires it.
 * </p>
 */
public interface ThreadObserver {

    /**
     * Message from the thread to for the observer.
     * 
     * @param sourceThread source thread for the notification
     * @param message message to transmit
     */
    void threadMessage(Thread sourceThread, String message);

    /**
     * Callback function used to notify the death of a thread.
     * 
     * @param sourceThread source thread for the notification
     */
    void threadDeath(Thread sourceThread);

}
