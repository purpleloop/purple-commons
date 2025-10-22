package io.github.purpleloop.commons.direction;

/**
 * Base class to models a direction in a 2D space.
 * 
 * This class, in itself is just a base to sub-class for defining directions in
 * various coordinences.
 */
public class Direction {

    /** The default none direction. */
    public static final Direction NONE = new Direction(-1, "NONE");

    /** Value of the direction. */
    protected int value;

    /** Name, string describing the direction. */
    private String name;

    /**
     * Constructor of the direction.
     * 
     * @param value Numerical value of the direction
     * @param name Name, string describing the direction
     */
    protected Direction(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * @return Numerical value of the direction
     */
    public int getValue() {
        return value;
    }

    /** @return moving step on the X-Axis for this direction. */
    public double getXStep() {
        return 0;
    }

    /** @return moving step on the Y-Axis for this direction */
    public double getYStep() {
        return 0;
    }

    /**
     * @return a direction that keeps only the horizontal component movement.
     */
    public Direction keepHorizontalMove() {
        return this;
    }

    /**
     * @return a direction that keeps only the vertical component movement.
     */
    public Direction keepVerticalMove() {
        return this;
    }

    /**
     * Gives the next direction.
     * 
     * @param clockWise true for a clockwise rotation, false for
     *            counter-clockwise
     * @return the next direction
     */
    public Direction next(boolean clockWise) {
        return NONE;
    }

    /**
     * Gives the next direction with a given increment.
     * 
     * @param increment a positive increment to turn clockwise, negative to turn
     *            clockwise, zero for keeping the same direction
     * @return the resulting direction
     */
    public Direction next(int increment) {
        return NONE;
    }

    /** @return the opposite direction */
    public Direction opposite() {
        return NONE;
    }

    @Override
    public String toString() {
        return this.name;
    }

    /** @return Angle in radians */
    public double getAngle() {
        return 0;
    }

}
