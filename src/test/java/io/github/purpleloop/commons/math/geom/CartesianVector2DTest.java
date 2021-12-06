package io.github.purpleloop.commons.math.geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import io.github.purpleloop.commons.math.NumberUtils;

/** Tests on vectors. */
public class CartesianVector2DTest {

    /** Test toString. */
    @Test
    public void testToStringOk() {
        assertEquals("(0.0, 1.0)", new CartesianVector2D(0, 1).toString());
    }

    /** Test equals - Ok same. */
    @Test
    public void testEqualsOkSame() {
        assertEquals(new CartesianVector2D(0, 1), new CartesianVector2D(0, 1));
    }

    /** Test equals - Ko different. */
    @Test
    public void testEqualsKoDifferent() {
        assertNotEquals(new CartesianVector2D(0, 1), new CartesianVector2D(1, 0));
    }

    /** Test norm - Ok unitary. */
    @Test
    public void testNormOkUnitary() {
        assertEquals(1.0, new CartesianVector2D(1, 0).getNorm(), NumberUtils.EPSILON);
    }

    /** Test norm - Ok sample. */
    @Test
    public void testNormOkSample() {
        assertEquals(5.0, new CartesianVector2D(3, 4).getNorm(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok zero angle.
     */
    @Test
    public void testAngleOkZero() {
        assertEquals(0.0, new CartesianVector2D(1, 0).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok right angle.
     */
    @Test
    public void testAngleOkRight() {
        assertEquals(Math.PI / 2.0, new CartesianVector2D(0, 1).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok flat angle.
     */
    @Test
    public void testAngleOkFlat() {
        assertEquals(Math.PI, new CartesianVector2D(-1, 0).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok right negative angle.
     */
    @Test
    public void testAngleOkRightNegative() {
        assertEquals(-Math.PI / 2.0, new CartesianVector2D(0, -1).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok sample angle.
     */
    @Test
    public void testAngleOkSample() {
        assertEquals(Math.PI / 6.0, new CartesianVector2D(Math.sqrt(3) / 2, 0.5).getAngle(),
                NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ko zero vector.
     * 
     */
    @Test
    public void testAngleKoZero() {
        assertEquals(0.0, new CartesianVector2D(0, 0).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test scale - Ok sample.
     */
    @Test
    public void testScaleOkSample() {
        assertEquals(new CartesianVector2D(7.5, 12.5), new CartesianVector2D(3, 5).scale(2.5));
    }

    /**
     * Test rotate - Ok sample negative right rotated right angle.
     */
    @Test
    public void testRotateOkSampleNegativeRightRotRight() {
        assertEquals(new CartesianVector2D(1, 0), new CartesianVector2D(0, -1).rotate(Math.PI / 2));
    }

    /**
     * Test rotate - Ok sample pi/6 rotated right angle.
     */
    @Test
    public void testRotateOkSamplePiSixthRotRight() {
        assertEquals(new CartesianVector2D(-0.5, Math.sqrt(3) / 2),
                new CartesianVector2D(Math.sqrt(3) / 2, 0.5).rotate(Math.PI / 2));
    }

    /**
     * Test add - Ok sample.
     */
    @Test
    public void testAddOkSample() {
        assertEquals(new CartesianVector2D(7, 9),
                new CartesianVector2D(2, 3).add(new CartesianVector2D(5, 6)));
    }

    /**
     * Test distance - Ok zero.
     */
    @Test
    public void testDistanceOkZero() {
        assertEquals(0.0, new CartesianVector2D(1, 0).distance(new CartesianVector2D(1, 0)),
                NumberUtils.EPSILON);
    }

    /**
     * Test distance - Ok sample.
     */
    @Test
    public void testDistanceOkSample() {
        assertEquals(Math.sqrt(2),
                new CartesianVector2D(1, 0).distance(new CartesianVector2D(0, 1)),
                NumberUtils.EPSILON);
    }

    /**
     * Test scale - Ok sample.
     */
    @Test
    public void testScaledOkSample() {
        assertEquals(new CartesianVector2D(7.5, 12.5),
                CartesianVector2D.createScaledVector(new CartesianVector2D(3, 5), 2.5));
    }

    /**
     * Test rotated - Ok sample negative right rotated right angle.
     */
    @Test
    public void testRotatedOkSampleNegativeRightRotRight() {
        assertEquals(new CartesianVector2D(1, 0),
                CartesianVector2D.createRotatedVector(new CartesianVector2D(0, -1), Math.PI / 2));
    }

    /**
     * Test rotated - Ok sample pi/6 rotated right angle.
     */
    @Test
    public void testRotatedOkSamplePiSixthRotRight() {
        assertEquals(new CartesianVector2D(-0.5, Math.sqrt(3) / 2), CartesianVector2D
                .createRotatedVector(new CartesianVector2D(Math.sqrt(3) / 2, 0.5), Math.PI / 2));
    }

}
