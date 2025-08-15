package io.github.purpleloop.commons.math.geom;

/**
 * Models a segment in a 2D space.
 * 
 * @param p1 first end of the segment
 * @param p2 second end of the segment
 */
public record Segment2D(Point2D p1, Point2D p2) {

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

        // If M is on the segment AB then it exists a real k in [0;1]
        // that satisfies AM = k . AB

        // AM.x = k * (AB.x)
        // AM.y = k * (AB.y)

        // k = (xm - xa) / (xb - xa)
        // k = (ym - ya) / (yb - ya)

        if (p2.x() - p1.x() != 0) {

            double k = (m.x() - p1.x()) / (p2.x() - p1.x());
            return k >= 0.0 && k <= 1.0;

        } else if (p2.y() - p1.y() != 0) {

            // Vertical segment, so check ordinates
            double k = (m.y() - p1.y()) / (p2.y() - p1.y());
            return k >= 0.0 && k <= 1.0;

        } else {

            // Segment is reduced to a point
            return (m.x() == p1.x() && m.y() == p1.y());
        }

    }

}
