package io.github.purpleloop.commons.direction;

import java.util.ArrayList;
import java.util.List;

/** Models direction with 8 positions and mapping to cardinals (coordination 8). */
public final class Direction8 extends EnumeratedDirection {

    /** East direction. */
    public static final Direction8 EAST = new Direction8(0, "EAST");

    /** North-East direction. */
    public static final Direction8 NORTH_EAST = new Direction8(1, "NORTH_EAST");

    /** North direction. */
    public static final Direction8 NORTH = new Direction8(2, "NORTH");

    /** North-West direction. */
    public static final Direction8 NORTH_WEST = new Direction8(3, "NORTH_WEST");

    /** West direction. */
    public static final Direction8 WEST = new Direction8(4, "WEST");

    /** South-West direction. */
    public static final Direction8 SOUTH_WEST = new Direction8(5, "SOUTH_WEST");

    /** South direction. */
    public static final Direction8 SOUTH = new Direction8(6, "SOUTH");

    /** South-East direction. */
    public static final Direction8 SOUTH_EAST = new Direction8(7, "SOUTH_EAST");

    /** Moves along X and Y axis for each direction. */
    private static final double[][] DIR = { { 1.0, 0.0 }, { 0.5, -0.5 }, { 0.0, -1.0 },
            { -0.5, -0.5 }, { -1.0, 0.0 }, { -0.5, 0.5 }, { 0.0, 1.0 }, { 0.5, 0.5 } };

    /** The possible directions. */
    private static ArrayList<Direction> values;

    static {
        values = new ArrayList<>();
        addValue(EAST);
        addValue(NORTH_EAST);
        addValue(NORTH);
        addValue(NORTH_WEST);
        addValue(WEST);
        addValue(SOUTH_WEST);
        addValue(SOUTH);
        addValue(SOUTH_EAST);
    }

    /**
     * Constructor of the direction.
     * 
     * @param value value of the direction
     * @param name name of the direction
     */
    private Direction8(int value, String name) {
        super(8, value, name);
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
     * @return lists or all directions for this coordination
     */
    public static List<Direction> values() {
        return values;
    }

    @Override
    protected Direction getDirectionForValue(int newValue) {
        return getDirection8ForValue(newValue);
    }

    /**
     * Gives the Direction8 for a given value.
     * 
     * @param value value of the direction to obtain (must be positive)
     * @return the requested value
     */
    public static Direction getDirection8ForValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value " + value + " is not a Direction8 value.");
        }
        return values().get(value % 8);
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
        if (this == NORTH_WEST || this == SOUTH_WEST) {
            return WEST;
        }
        
        if (this == NORTH_EAST || this == SOUTH_EAST) {
            return EAST;
        }

        if (this == EAST || this == WEST) {
            return this;
        }

        return Direction.NONE;
    }

    @Override
    public Direction keepVerticalMove() {
        if (this == NORTH_EAST || this == NORTH_WEST) {
            return NORTH;
        }
        if (this == SOUTH_WEST || this == SOUTH_EAST) {
            return SOUTH;
        }

        if (this == NORTH || this == SOUTH) {
            return this;
        }

        return Direction.NONE;
    }

    @Override
    public double getAngle() {
        return ((double) getValue()) * Math.PI / 4.0;
    }

}
