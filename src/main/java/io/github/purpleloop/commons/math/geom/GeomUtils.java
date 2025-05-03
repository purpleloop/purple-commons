package io.github.purpleloop.commons.math.geom;

/** Geometric utilities. */
public final class GeomUtils {

    /** Private constructor. */
    private GeomUtils() {
    }

    /**
     * This method computes the distance between two points whose coordinates
     * are given.
     * 
     * @param x1 horizontal position of the first point M1
     * @param y1 vertical position of the first point M1
     * @param x2 horizontal position of the first point M2
     * @param y2 vertical position of the first point M2
     * @return double Distance between M1(x1,y1) and M2(x2,y2)
     */
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    /**
     * Given the point M(x,y), computes the angle for the segment OM, with
     * horizontal axis.
     * 
     * @param x
     * @param y
     * @return angle of the segment
     */
    public static double angleForSegment(double x, double y) {
        double ang;

        if (x != 0) {
            ang = Math.atan(y / x);
        } else if (y < 0) {
            ang = -Math.PI / 2;
        } else {
            ang = Math.PI / 2;
        }

        if (x < 0) {
            ang = ang + Math.PI;
        }
        return ang;
    }

}
