package io.github.purpleloop.commons.lang;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/** A class with useful stuffs for random things. */
public final class Randoms {

    /** Empty constructor. */
    private Randoms() {
    }

    /** @return random singleton */
    public static Random getRandom() {
        return ThreadLocalRandom.current();
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
