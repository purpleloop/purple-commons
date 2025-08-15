package io.github.purpleloop.commons.math.geom;

import java.util.Optional;

/**
 * Models a Cartesian equation of a line <em>a.x + b.y + c = 0</em>.
 * 
 * @param a a parameter of the equation
 * @param b b parameter of the equation
 * @param c c parameter of the equation
 */
public record CartesianLineEquation(double a, double b, double c) {

    /** @return a direction vector of the line */
    public CartesianVector2D getDirectionVector() {
        return new CartesianVector2D(-b, a);
    }

    /** @return a normal vector of the line */
    public CartesianVector2D getNormalVector() {
        return new CartesianVector2D(a, b);
    }

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
     * @return an optional Cartesian equation of the line if it exists
     */
    public static Optional<CartesianLineEquation> fromPoints(double x1, double y1, double x2,
            double y2) {

        if ((x1 == x2) && (y1 == y2)) {

            // Points are equals no equation parameter can be computed
            return Optional.empty();
        }

        double a = y2 - y1;
        double b = x1 - x2;
        double c = -(b * y1 + a * x1);

        return Optional.of(new CartesianLineEquation(a, b, c));
    }

    /**
     * Determines the Cartesian equation of the perpendicular bisector of a
     * segment AB given by its's coordinates.
     * 
     * @param x1 abscissa of the first point A
     * @param y1 ordinate of the first point A
     * @param x2 abscissa of the second point B
     * @param y2 ordinate of the second point B
     * 
     * @return an optional Cartesian equation of the line if the equation exists
     */
    public static Optional<CartesianLineEquation> bisectorForSegment(double x1, double y1,
            double x2, double y2) {

        // Middle point I for the segment AB
        double xi = (x1 + x2) / 2.0;
        double yi = (y1 + y2) / 2.0;

        // Vector components for segment AB
        double abx = x2 - x1;
        double aby = y2 - y1;

        // AB is a vector normal of the perpendicular bisector
        double a = abx;
        double b = aby;

        // Perpendicular bisector contains point I
        double c = -(b * yi + a * xi);

        return Optional.of(new CartesianLineEquation(a, b, c));
    }

}
