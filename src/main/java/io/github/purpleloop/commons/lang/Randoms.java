package io.github.purpleloop.commons.lang;

import java.util.Random;

/** A class with useful stuffs for random things. */
public final class Randoms {

    /** Empty constructor. */
    private Randoms() {
    }

    /** Random singleton. */
    private static Random rnd;

    /** @return random singleton */
    public static synchronized Random getRandom() {
        if (rnd == null) {
            rnd = new Random();
        }

        return rnd;
    }

    /**
     * Returns a pseudo-random number between 0 and n-1.
     * 
     * @param n the upper bound (excluded) of the generation (positive integer).
     * @return the next random integer
     */
    public static int nextRandomInt(int n) {
        return getRandom().nextInt(n);
    }

}
