package io.github.purpleloop.commons.direction;

import java.util.ArrayList;
import java.util.List;

/** Models directions with 2 positions horizontally (left and right). */
public final class Direction2H extends EnumeratedDirection {

    /** Constant for the direction indicating the left. */
    public static final Direction LEFT = new Direction2H(0, "LEFT");

    /** Constant for the direction indicating the right. */
    public static final Direction RIGHT = new Direction2H(1, "RIGHT");

    /** Moves along X and Y axis for each direction. */
    private static final double[][] DIR = { { -1.0, 0.0 }, { 1.0, 0.0 } };

    /** The possible directions. */
    private static ArrayList<Direction> values;

    static {
        values = new ArrayList<>();
        addValue(LEFT);
        addValue(RIGHT);
    }

    /**
     * Constructor of the direction.
     * 
     * @param value value of the direction
     * @param name name of the direction
     */
    protected Direction2H(int value, String name) {
        super(2, value, name);
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
        if (newValue == 0) {
            return Direction2H.LEFT;
        } else {
            return Direction2H.RIGHT;
        }
    }

    @Override
    public double getXStep() {
        return DIR[value][0];
    }

    @Override
    public double getYStep() {
        return DIR[value][1];
    }

}
