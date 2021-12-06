package io.github.purpleloop.commons.math.geom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import io.github.purpleloop.commons.math.NumberUtils;

/** Tests on polar vectors. */
public class PolarVector2DTest2 {

    /** Test toString. */
    @Test
    public void testToStringOk() {
        assertEquals("(r:1.0, th:1.571 or 90 deg)", new PolarVector2D(1, Math.PI / 2).toString());
    }

    /** Test equals - Ok same. */
    @Test
    public void testEqualsOkSame() {
        assertEquals(new PolarVector2D(1, Math.PI / 2), new PolarVector2D(1, Math.PI / 2));
    }

    /** Test equals - Ko different. */
    @Test
    public void testEqualsKoDifferent() {
        assertNotEquals(new PolarVector2D(1, Math.PI / 2), new PolarVector2D(1, 0));
    }

    /** Test norm - Ok unitary. */
    @Test
    public void testNormOkUnitary() {
        assertEquals(1.0, new PolarVector2D(1, Math.PI / 2).getNorm(), NumberUtils.EPSILON);
    }

    /** Test norm - Ok sample. */
    @Test
    public void testNormOkSample() {
        assertEquals(2, new PolarVector2D(2, Math.PI / 6).getNorm(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok zero angle.
     */
    @Test
    public void testAngleOkZero() {
        assertEquals(0.0, new PolarVector2D(1, 0).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok right angle.
     */
    @Test
    public void testAngleOkRight() {
        assertEquals(Math.PI / 2.0, new PolarVector2D(1, Math.PI / 2).getAngle(),
                NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok flat angle.
     * 
     * @in case of error
     */
    @Test
    public void testAngleOkFlat() {
        assertEquals(Math.PI, new PolarVector2D(1, Math.PI).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok right negative angle.
     * 
     * @in case of error
     */
    @Test
    public void testAngleOkRightNegative() {
        assertEquals(-Math.PI / 2.0, new PolarVector2D(1, -Math.PI / 2).getAngle(),
                NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ok sample angle.
     * 
     * @in case of error
     */
    @Test
    public void testAngleOkSample() {
        assertEquals(Math.PI / 6.0, new PolarVector2D(1, Math.PI / 6).getAngle(),
                NumberUtils.EPSILON);
    }

    /**
     * Test angle - Ko zero vector.
     * 
     */
    @Test
    public void testAngleKoZero() {
        assertEquals(0.0, new PolarVector2D(0, 0).getAngle(), NumberUtils.EPSILON);
    }

    /**
     * Test scale - Ok sample.
     */
    @Test
    public void testScaleOkSample() {
        assertEquals(new PolarVector2D(7.5, Math.PI / 6),
                new PolarVector2D(3, Math.PI / 6).scale(2.5));
    }

    /**
     * Test rotate - Ok sample negative right rotated right angle.
     */
    @Test
    public void testRotateOkSampleNegativeRightRotRight() {
        assertEquals(new PolarVector2D(1, 0),
                new PolarVector2D(1, -Math.PI / 2).rotate(Math.PI / 2));
    }

    /**
     * Test rotate - Ok sample pi/6 rotated right angle.
     */
    @Test
    public void testRotateOkSamplePiSixthRotRight() {
        assertEquals(new PolarVector2D(1, 2.0 * Math.PI / 3.0),
                new PolarVector2D(1, Math.PI / 6.0).rotate(Math.PI / 2.0));
    }

    /**
     * Test add - Ok sample.
     */
    @Test
    public void testAddOkSample() {
        assertEquals(new PolarVector2D(2 * Math.sqrt(2), Math.PI / 2),
                new PolarVector2D(2.0, Math.PI / 4.0)
                        .add(new PolarVector2D(2.0, 3.0 * Math.PI / 4.0)));
    }

    /**
     * Test distance - Ok zero.
     */
    @Test
    public void testDistanceOkZero() {
        assertEquals(0.0, new PolarVector2D(1, 0).distance(new PolarVector2D(1, 0)),
                NumberUtils.EPSILON);
    }

    /**
     * Test distance - Ok sample.
     */
    @Test
    public void testDistanceOkSample() {
        assertEquals(Math.sqrt(2),
                new PolarVector2D(1, 0).distance(new PolarVector2D(1, Math.PI / 2)),
                NumberUtils.EPSILON);
    }

    /**
     * Test scale - Ok sample.
     */
    @Test
    public void testScaledOkSample() {
        assertEquals(new PolarVector2D(7.5, Math.PI / 4),
                PolarVector2D.createScaledVector(new PolarVector2D(3, Math.PI / 4), 2.5));
    }

    /**
     * Test rotated - Ok sample negative right rotated right angle.
     */
    @Test
    public void testRotatedOkSampleNegativeRightRotRight() {
        assertEquals(new PolarVector2D(1, 0),
                PolarVector2D.createRotatedVector(new PolarVector2D(1, -Math.PI / 2), Math.PI / 2));
    }

    /**
     * Test rotated - Ok sample pi/6 rotated right angle.
     */
    @Test
    public void testRotatedOkSamplePiSixthRotRight() {
        assertEquals(new PolarVector2D(1, 2 * Math.PI / 3),
                PolarVector2D.createRotatedVector(new PolarVector2D(1, Math.PI / 6), Math.PI / 2));
    }

}
