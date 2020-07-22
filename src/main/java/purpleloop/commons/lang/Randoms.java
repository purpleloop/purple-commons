package purpleloop.commons.lang;

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

}
