package io.github.purpleloop.commons.direction;

/** Models directions for a given coordination by enumeration. */
public abstract class EnumeratedDirection extends Direction {

    /** The coordination, number of distinct directions on the circle. */
    private int coordination;

    /**
     * Creates an enumerated direction.
     * 
     * @param coordination the coordination
     * @param value the value to add
     * @param name name associated to the value
     */
    protected EnumeratedDirection(int coordination, int value, String name) {

        super(value, name);
        this.coordination = coordination;
    }

    /**
     * @return the coordination
     */
    public int getCoordination() {
        return coordination;
    }

    @Override
    public Direction next(boolean clockWise) {
        return next(clockWise ? 1 : getCoordination() - 1);
    }

    @Override
    public Direction next(int increment) {

        int newValue = (value + increment);
        if (newValue >= coordination) {
            newValue = newValue % coordination;
        } else if (newValue < 0) {
            newValue = newValue + coordination;
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

        int coord = getCoordination();
        return getDirectionForValue((value + coord / 2) % coord);
    }
}
