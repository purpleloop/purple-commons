package purpleloop.commons.math;

/** Analysis utilities. */
public final class AnalysisUtils {

    /** Private constructor for utility class. */
    private AnalysisUtils() {
    }

    /**
     * Get a bounded value between two extremal.
     * 
     * <p>
     * Gives value if it is between bounds, the minimal if the value is below or
     * the maximal if the value is above
     * </p>
     * 
     * <p>
     * The function rises an IllegalArgumentException if minimal is higher than
     * maximal.
     * </p>
     * 
     * @param value value to bound
     * @param min minimal value to return
     * @param max maximal value to return
     * @return bounded value
     */
    public static int bound(int value, int min, int max) {

        if (max < min) {
            throw new IllegalArgumentException("Maximal value should be lower than minimal value.");
        } else if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        }
        return value;
    }

}
