package io.github.purpleloop.commons.direction;

/** Models directions for a given coordinence by enumeration. */
public abstract class EnumeratedDirection extends Direction {

    /** The coordinence, number of distinct directions on the circle. */
    private int coordinence;

    /**
     * Creates an enumerated direction.
     * 
     * @param coordinence the coordinence
     * @param value the value to add
     * @param name name associated to the value
     */
    protected EnumeratedDirection(int coordinence, int value, String name) {

        super(value, name);
        this.coordinence = coordinence;
    }

    /**
     * @return the coordinence
     */
    public int getCoordinence() {
        return coordinence;
    }

    @Override
    public Direction next(boolean clockWise) {
        return next(clockWise ? 1 : getCoordinence() - 1);
    }

    @Override
    public Direction next(int increment) {

        int newValue = (value + increment);
        if (newValue >= coordinence) {
            newValue = newValue % coordinence;
        } else if (newValue < 0) {
            newValue = newValue + coordinence;
        }

        return getDirectionForValue(newValue);
    }

    /**
     * Gives the Direction for a given value.
     * 
     * @param value value of the direction to obtain (must be positive)
     * @return the requested value
     */
    protected abstract Direction getDirectionForValue(int value);

    @Override
    public Direction opposite() {

        int coord = getCoordinence();
        return getDirectionForValue((value + coord / 2) % coord);
    }
}
