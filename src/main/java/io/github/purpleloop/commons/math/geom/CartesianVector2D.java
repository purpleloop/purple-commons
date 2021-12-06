package io.github.purpleloop.commons.math.geom;

import java.util.Objects;

import io.github.purpleloop.commons.math.NumberUtils;

/**
 * Implementation of a vector using doubles for 2D Cartesian coordinates.
 */
public class CartesianVector2D implements Vector2D {

    /** X component. */
    private double x;

    /** Y component. */
    private double y;

    /**
     * Constructs a new vector from coordinates.
     * 
     * @param x X component
     * @param y Y component
     */
    public CartesianVector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a new vector from another one.
     * 
     * @param other another vector
     */
    public CartesianVector2D(Vector2D other) {
        this.x = other.getX();
        this.y = other.getY();
    }

    /** @param x X component */
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getX() {
        return x;
    }

    /** @param y Y component */
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getNorm() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * @return the vector angle in radians
     */
    public double getAngle() {
        return Math.atan2(y, x);
    }

    @Override
    public Vector2D resetUnity() {
        this.x = 1;
        this.y = 0;
        return this;
    }

    @Override
    public void setCartesian(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setPolar(double norm, double angle) {
        this.x = norm * Math.cos(angle);
        this.y = norm * Math.sin(angle);
    }

    @Override
    public Vector2D add(Vector2D other) {
        this.x += other.getX();
        this.y += other.getY();
        return this;
    }

    @Override
    public Vector2D scale(double scale) {
        this.x *= scale;
        this.y *= scale;
        return this;
    }

    @Override
    public Vector2D rotate(double angle) {
        double ox = x;
        double oy = y;

        this.x = ox * Math.cos(angle) - oy * Math.sin(angle);
        this.y = ox * Math.sin(angle) + oy * Math.cos(angle);

        return this;
    }

    @Override
    public double distance(Vector2D other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
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
        return NumberUtils.equals(x, otherVector.getX())
                && NumberUtils.equals(y, otherVector.getY());
    }

    /**
     * Creates a vector scaled from the given one (which is preserved).
     * 
     * @param source the source vector
     * @param scale a scale factor
     * @return a new vector, derived from the source and scaled as requested
     */
    public static CartesianVector2D createScaledVector(CartesianVector2D source, double scale) {
        return new CartesianVector2D(source.x * scale, source.y * scale);
    }

    /**
     * Creates a vector rotated from the given one (which is preserved).
     * 
     * @param source the source vector
     * @param angle rotation angle in radians
     * @return a new vector, derived from the source and rotated as requested
     */
    public static CartesianVector2D createRotatedVector(CartesianVector2D source, double angle) {
        return new CartesianVector2D(source.x * Math.cos(angle) - source.y * Math.sin(angle),
                source.x * Math.sin(angle) + source.y * Math.cos(angle));
    }

}
