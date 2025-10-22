package io.github.purpleloop.commons.direction;

import java.util.ArrayList;
import java.util.List;

/** Models directions with 4 position and mapping to cardinals (coordinence 4). */
public final class Direction4 extends EnumeratedDirection {

    /** Constant for the direction indicating the east. */
    public static final Direction EAST = new Direction4(0, "EAST");

    /** Constant for the direction indicating the north. */
    public static final Direction NORTH = new Direction4(1, "NORTH");

    /** Constant for the direction indicating the west. */
    public static final Direction WEST = new Direction4(2, "WEST");

    /** Constant for the direction indicating the south. */
    public static final Direction SOUTH = new Direction4(3, "SOUTH");

    /** Moves along X and Y axis for each direction. */
    private static final double[][] DIR = { { 1.0, 0.0 }, { 0.0, -1.0 }, { -1.0, 0.0 },
            { 0.0, 1.0 } };

    /** The possible directions. */
    private static ArrayList<Direction> values;

    static {
        values = new ArrayList<>();
        addValue(EAST);
        addValue(NORTH);
        addValue(WEST);
        addValue(SOUTH);
    }

    /**
     * Constructor of the direction.
     * 
     * @param value value of the direction
     * @param name name of the direction
     */
    protected Direction4(int value, String name) {
        super(4, value, name);

    }

    /**
     * Adds a value to the internal directions.
     * 
     * @param dir the direction to add
     */
    protected static void addValue(Direction dir) {
        values.add(dir);
    }

    /**
     * List of all possible directions.
     * 
     * @return lists or all directions for this coordinence
     */
    public static List<Direction> values() {
        return values;
    }

    @Override
    protected Direction getDirectionForValue(int newValue) {
        return getDirection4ForValue(newValue);
    }

    /**
     * Gives the Direction4 for a given value.
     * 
     * @param value value of the direction to obtain (must be positive)
     * @return the requested value
     */
    public static Direction getDirection4ForValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value " + value + " is not a Direction4 value.");
        }
        return values().get(value % 4);
    }

    @Override
    public double getXStep() {
        return DIR[value][0];
    }

    @Override
    public double getYStep() {
        return DIR[value][1];
    }

    @Override
    public Direction keepHorizontalMove() {
        if (this == EAST || this == WEST) {
            return this;
        }

        return Direction.NONE;
    }

    @Override
    public Direction keepVerticalMove() {
        if (this == NORTH || this == SOUTH) {
            return this;
        }

        return Direction.NONE;
    }

    @Override
    public double getAngle() {
        return ((double) getValue()) * Math.PI / 2.0;
    }

}
