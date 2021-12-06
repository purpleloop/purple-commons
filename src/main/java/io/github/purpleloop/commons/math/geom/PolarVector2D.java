package io.github.purpleloop.commons.math.geom;

import java.util.Objects;

import io.github.purpleloop.commons.math.NumberUtils;

/** Implementation of a vector using doubles for 2D polar coordinates. */
public class PolarVector2D implements Vector2D {

    /** Norm of the vector. */
    double norm;

    /** Angle of the vector with the [O, x) axis. */
    double angle;

    /**
     * Creates a vector from polar coordinates.
     * 
     * @param norm then norm
     * @param angle the angle
     */
    public PolarVector2D(double norm, double angle) {
        this.norm = norm;
        this.angle = angle;
    }

    /**
     * Constructs a new vector from another one.
     * 
     * @param other another vector
     */
    public PolarVector2D(Vector2D other) {
        this.norm = other.getNorm();
        this.angle = other.getAngle();
    }

    @Override
    public double getX() {
        return norm * Math.cos(angle);
    }

    @Override
    public double getY() {
        return norm * Math.sin(angle);
    }

    @Override
    public double getNorm() {
        return norm;
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public Vector2D resetUnity() {
        this.norm = 1;
        this.angle = 0;
        return this;
    }

    @Override
    public void setCartesian(double x, double y) {
        this.norm = Math.sqrt(x * x + y * y);
        this.angle = Math.atan2(y, x);
    }

    @Override
    public void setPolar(double norm, double angle) {
        this.norm = norm;
        this.angle = angle;
    }

    @Override
    public Vector2D add(Vector2D other) {

        double r1 = norm;
        double r2 = other.getNorm();

        double th1 = angle;
        double th2 = other.getAngle();

        this.norm = Math.sqrt(r1 * r1 + r2 * r2 + 2 * r1 * r2 * Math.cos(th2 - th1));

        this.angle = Math.atan2(r1 * Math.sin(th1) + r2 * Math.sin(th2),
                r1 * Math.cos(th1) + r2 * Math.cos(th2));

        return this;
    }

    @Override
    public Vector2D scale(double scale) {
        this.norm *= scale;
        return this;
    }

    @Override
    public Vector2D rotate(double angle) {
        this.angle += angle;
        simplifyAngle();
        return this;
    }

    /** Simplifies angles between -Pi and Pi. */
    private void simplifyAngle() {
        if (this.angle > Math.PI) {
            this.angle -= 2 * Math.PI;
        }
        if (this.angle < -Math.PI) {
            this.angle += 2 * Math.PI;
        }
    }

    @Override
    public double distance(Vector2D other) {

        // https://socratic.org/questions/what-is-the-formula-for-the-distance-between-two-polar-coordinates

        double r1 = this.norm;
        double r2 = other.getNorm();
        double th1 = this.angle;
        double th2 = other.getAngle();

        return Math.sqrt(r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(th1 - th2));
    }

    @Override
    public String toString() {

        return "(r:" + this.norm + ", th:" + Math.round(this.angle * 1000.0) / 1000.0 + " or "
                + ((int) (this.angle / Math.PI * 180)) + " deg)";
    }

    @Override
    public int hashCode() {
        return Objects.hash(norm, angle);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Vector2D)) {
            return false;
        }

        Vector2D otherVector = (Vector2D) other;
        return NumberUtils.equals(norm, otherVector.getNorm())
                && NumberUtils.equals(angle, otherVector.getAngle());
    }

    /**
     * Creates a vector scaled from the given one (which is preserved).
     * 
     * @param source the source vector
     * @param scale a scale factor
     * @return a new vector, derived from the source and scaled as requested
     */
    public static Vector2D createScaledVector(Vector2D source, double scale) {
        return new PolarVector2D(source.getNorm() * scale, source.getAngle());
    }

    /**
     * Creates a vector rotated from the given one (which is preserved).
     * 
     * @param source the source vector
     * @param angle rotation angle in radians
     * @return a new vector, derived from the source and rotated as requested
     */
    public static Vector2D createRotatedVector(Vector2D source, double angle) {
        PolarVector2D vector = new PolarVector2D(source.getNorm(), source.getAngle());
        vector.rotate(angle);
        return vector;
    }

}
