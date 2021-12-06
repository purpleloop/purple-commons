package io.github.purpleloop.commons.math.geom;

/** Interface for a vector in 2D. */
public interface Vector2D {

    /** @return X component */
    double getX();

    /** @return Y component */
    double getY();

    /**
     * @return norm of the vector
     */
    double getNorm();

    /**
     * @return the vector angle in radians
     */
    double getAngle();

    /**
     * Change to Cartesian coordinates.
     * 
     * @param x X component
     * @param y Y component
     */
    void setCartesian(double x, double y);

    /**
     * Change to polar coordinates.
     * 
     * @param norm then norm
     * @param angle the angle
     */
    void setPolar(double norm, double angle);

    /**
     * Reset to unity vector (1, 0).
     * 
     * @return this vector
     */
    Vector2D resetUnity();

    /**
     * Adds another vector to the end of the current one.
     * 
     * @param other the vector to add
     * @return this vector, after addition
     */
    Vector2D add(Vector2D other);

    /**
     * Scales the vector with the given scale factor.
     * 
     * @param scale a scale factor
     * @return a new vector, derived from the source and scaled as requested
     */
    Vector2D scale(double scale);

    /**
     * Rotates the vector with the given angle.
     * 
     * @param angle rotation angle in radians
     * @return the vector, rotated as requested
     */
    Vector2D rotate(double angle);

    /**
     * Distance between vector ends.
     * 
     * @param other the other vector
     * @return distance to the other vector ends
     */
    double distance(Vector2D other);

    /**
     * Check bounds and applies boundary mode to out of bound coordinates.
     * 
     * @param width width of the bounding box
     * @param height height of the bounding box
     * @param boundaryMode boundary mode to apply
     */
    default void bound(int width, int height, BoundaryMode boundaryMode) {

        double cx = getX();
        double cy = getY();
        double nx = cx;
        double ny = cy;

        if (boundaryMode == BoundaryMode.TRANSLATE) {

            if (cx > width) {
                nx = cx - width;
            } else if (cx < 0) {
                nx = cx + width;
            }
            if (cy > height) {
                ny = cy - height;
            } else if (cy < 0) {
                ny = cy + height;
            }
        } else if (boundaryMode == BoundaryMode.BOUNCE) {

            if (cx > width) {
                nx = width - cx;
            } else if (cx < 0) {
                nx = -cx;
            }
            if (cy > height) {
                ny = height - cy;
            } else if (cy < 0) {
                ny = -cy;
            }

        }
        setCartesian(nx, ny);
    }

}
