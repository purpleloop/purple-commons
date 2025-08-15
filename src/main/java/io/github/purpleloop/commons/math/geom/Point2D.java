package io.github.purpleloop.commons.math.geom;

/**
 * A point in 2 dimensions with double coordinates.
 * 
 * @param x abscissa
 * @param y ordinate
 */
public record Point2D(double x, double y) {

    /** Middle of two points.
     * 
     * @param p1 first point
     * @param p2 second point
     * @return middle point
     */
    public static Point2D middle(Point2D p1, Point2D p2) {
        return middle(p1.x(), p1.y(), p2.x(), p2.y());
    }

    /** Middle of two points given by their coordinates.
     * 
     * @param x1 first point abscissa
     * @param y1 first point ordinate
     * @param x2 second point abscissa
     * @param y2 second point ordinate
     * @return middle point
     */
    private static Point2D middle(double x1, double y1, double x2, double y2) {
        return new Point2D((x1 + x2) / 2.0, (y1 + y2) / 2.0);
    }

}
