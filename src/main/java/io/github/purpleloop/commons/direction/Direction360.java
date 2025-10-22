package io.github.purpleloop.commons.direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Models a direction on 360 degrees.
 * 
 * The directions will be named from D0 to D359.
 */
public class Direction360 extends EnumeratedDirection {

    /** Number or degrees in a circle. */
    private static final int DEGREES_IN_CIRCLE = 360;

    /** Moves along X and Y axis for each direction. */
    private static final double[][] DIR;

    /** The possible values. */
    private static ArrayList<Direction> values;

    static {

        values = new ArrayList<>();
        DIR = new double[DEGREES_IN_CIRCLE][2];

        for (int value = 0; value < DEGREES_IN_CIRCLE; value++) {

            addValue(new Direction360(value, "D" + value));
            DIR[value][0] = Math.cos((double) value / ((double) DEGREES_IN_CIRCLE) * 2.0 * Math.PI);
            DIR[value][1] = Math.sin((double) value / ((double) DEGREES_IN_CIRCLE) * 2.0 * Math.PI);
        }
    }

    /**
     * Constructor of the direction.
     * 
     * @param value Numerical value of the direction
     * @param name Name, string describing the direction
     */
    protected Direction360(int value, String name) {
        super(DEGREES_IN_CIRCLE, value, name);
    }

    /**
     * Adds a value to the internal directions.
     * 
     * @param directions the direction to add
     */
    protected static void addValue(Direction directions) {
        values.add(directions);
    }

    /**
     * List of all possible directions.
     * 
     * @return lists or all directions for this coordinence
     */
    public static List<Direction> values() {
        return values;
    }
    
    /**
     * Gives the Direction for a given value.
     * 
     * @param value value of the direction to obtain (must be positive)
     * @return the requested value
     */    
    protected Direction getDirectionForValue(int value) {
        return getDirection360ForValue(value);
    }
    
    /**
     * Gives the Direction360 for a given value.
     * 
     * @param value value of the direction to obtain (must be positive)
     * @return the requested value
     */
    public static Direction getDirection360ForValue(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value " + value + " is not a Direction360 value.");
        }
        return values().get(value % DEGREES_IN_CIRCLE);
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
        return descaleDirection8().keepHorizontalMove();
    }

    @Override
    public Direction keepVerticalMove() {
        return descaleDirection8().keepVerticalMove();
    }

    @Override
    public Direction next(boolean clockWise) {

        if (clockWise) {
            return next(1);
        } else {
            if (value > 0) {
                return getDirectionForValue(value - 1);
            } else {
                return getDirectionForValue(value + (DEGREES_IN_CIRCLE - 1));
            }
        }
    }

    /**
     * Downgrades the precision to obtain a coordinence 8 direction.
     * 
     * @return direction downgraded to Direction8
     */
    public Direction8 descaleDirection8() {
        return (Direction8) Direction8.getDirection8ForValue(value * 8 / DEGREES_IN_CIRCLE);
    }

    /**
     * Downgrades the precision to obtain a coordinence 4 direction.
     * 
     * @return direction downgraded to Direction8
     */
    public Direction4 descaleDirection4() {
        return (Direction4) Direction4.getDirection4ForValue(value * 4 / DEGREES_IN_CIRCLE);
    }

    @Override
    public double getAngle() {
        return ((double) getValue()) * Math.PI / 180.0;
    }

}
