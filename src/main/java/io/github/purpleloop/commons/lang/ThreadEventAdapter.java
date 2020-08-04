package io.github.purpleloop.commons.lang;

/** An empty implementation of the thread observer. */
public class ThreadEventAdapter implements ThreadObserver {

    @Override
    public void threadMessage(Thread sourceThread, String message) {
    }

    @Override
    public void threadDeath(Thread sourceThread) {
    }

}
