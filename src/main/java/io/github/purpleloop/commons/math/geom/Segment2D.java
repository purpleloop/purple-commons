package io.github.purpleloop.commons.math.geom;

/**
 * Models a segment in a 2D space.
 * 
 * @param p1 first end of the segment
 * @param p2 second end of the segment
 */
public record Segment2D(Point2D p1, Point2D p2) {

    /** Numeric tolerance used for floating-point comparisons. */
    private static final double EPS = 1e-9;

    /** @return the middle of the segment */
    public Point2D middle() {
        return Point2D.middle(p1, p2);
    }

    /**
     * Tests if the given point is on the segment.
     * 
     * @param m point to test
     * @return true if the point is on the segment, false otherwise
     */
    public boolean contains(Point2D m) {

        // Vector AB = p2 - p1
        double abx = p2.x() - p1.x();
        double aby = p2.y() - p1.y();

        // Vector AM = m - p1
        double amx = m.x() - p1.x();
        double amy = m.y() - p1.y();

        // Squared length of AB
        double abNorm = abx * abx + aby * aby;

        // If segment is (almost) degenerate (p1 ≈ p2), treat it as a single
        // point.
        if (abNorm <= EPS * EPS) {
            return Math.abs(amx) <= EPS && Math.abs(amy) <= EPS;
        }

        // Cross product to check collinearity: AB x AM = 0 if collinear
        double cross = abx * amy - aby * amx;

        // If not collinear within EPS, it's not on the segment
        if (Math.abs(cross) > EPS) {
            return false;
        }

        // If M is on the segment AB then it exists a real k in [0;1]

        // Compute projection factor k = (AM . AB) / (AB . AB)
        double dot = abx * amx + aby * amy;
        double k = dot / abNorm;

        return k >= -EPS && k <= 1.0 + EPS;
    }

}
