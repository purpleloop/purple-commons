package io.github.purpleloop.commons.math.geom;

import java.util.Optional;

/**
 * Models a linear equation <em>a.x + b.y + c = 0</em>.
 * 
 * @param a a parameter of the equation
 * @param b b parameter of the equation
 * @param c c parameter of the equation
 */
public record LinearEquation(double a, double b, double c) {

    /**
     * Compute the equation value that must return zero if the given point is on
     * the line.
     * 
     * @param x abscissa
     * @param y ordinate
     * @return the value, zero expected for a point (x, y) on the line or else a
     *         non zero value
     */
    public double computesValueForPoint(double x, double y) {
        return a * x + b * y + c;
    }

    /**
     * Check if a given point (x, y) is on the line.
     * 
     * @param x abscissa
     * @param y ordinate
     * @param eps precision
     * @return true if the point is close enough of the line
     */
    public boolean isOnTheline(double x, double y, double eps) {

        double value = computesValueForPoint(x, y);
        return Math.abs(value) < eps;
    }

    /**
     * Computes the equation parameters for a line passing through two points.
     * 
     * @param x1 abscissa of the first point
     * @param y1 ordinate of the first point
     * @param x2 abscissa of the second point
     * @param y2 ordinate of the second point
     * 
     * @return an optional linear equation if the equation exists
     */
    public static Optional<LinearEquation> fromPoints(double x1, double y1, double x2, double y2) {

        if ((x1 == x2) && (y1 == y2)) {

            // Points are equals no equation parameter can be computed
            return Optional.empty();
        }

        double a = y2 - y1;
        double b = x1 - x2;
        double c = -(b * y1 + a * x1);

        return Optional.of(new LinearEquation(a, b, c));
    }

}
