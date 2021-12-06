package io.github.purpleloop.commons.math.geom;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test cases for geometric utilities. */
public class GeomUtilsTest {

    /** A precision value used to compare doubles. */
    private static final double EPSILON = 0.000001;

    /** Test when the distance is equals to zero. */
    @Test
    public void distanceSamePointTest() {
        assertEquals(0.0, GeomUtils.distance(20.0, 30.0, 20.0, 30.0), EPSILON);
    }

    /** Test for an horizontal segment distance. */
    @Test
    public void distanceHorizontalTest() {
        assertEquals(30.0, GeomUtils.distance(10.0, 0.0, 40.0, 0.0), EPSILON);
    }

    /** Test for a vertical segment distance. */
    @Test
    public void distanceVerticalTest() {
        assertEquals(40.0, GeomUtils.distance(0.0, 20.0, 0.0, 60.0), EPSILON);
    }

    /** Test for an easy computable diagonal segment distance. */
    @Test
    public void distanceEasyComputableTest() {
        assertEquals(50.0, GeomUtils.distance(10.0, 20.0, 50.0, 50.0), EPSILON);
    }

    /** Test for a 'square root of two' segment distance. */
    @Test
    public void distanceSquareRootOfTwoTest() {
        assertEquals(Math.sqrt(2.0), GeomUtils.distance(0.0, 0.0, 1.0, 1.0), EPSILON);
    }

    /**
     * Test for an easy computable diagonal segment distance with negative
     * coordinates.
     */
    @Test
    public void distanceEasyComputableNegativeTest() {
        assertEquals(50.0, GeomUtils.distance(-10.0, -20.0, -50.0, -50.0), EPSILON);
    }

}
