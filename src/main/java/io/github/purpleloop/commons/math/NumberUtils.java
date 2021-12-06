package io.github.purpleloop.commons.math;

/** Utilities about numbers. */
public final class NumberUtils {

    /** A negligible value. */
    public static final double EPSILON = 1.0E-6;

    /** Private constructor. */
    private NumberUtils() {
        // Nothing here
    }

    /**
     * Equality between doubles, under {@link #EPSILON} approximation.
     * 
     * @param x left double value
     * @param y right double value
     * @return equality of the two doubles
     */
    public static boolean equals(double x, double y) {
        return Math.abs(x - y) < EPSILON;
    }

}
