package io.github.purpleloop.commons.math.geom;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Tests for points. */
class Point2DTest {

    @Test
    void middleTestSample() {
        Point2D a = new Point2D(1, 1);
        Point2D b = new Point2D(3, 2);

        Point2D i = Point2D.middle(a, b);

        assertEquals(2.0, i.x());
        assertEquals(1.5, i.y());
    }

    @Test
    void middleTestSinglePoint() {
        Point2D a = new Point2D(1, 1);
        Point2D b = new Point2D(1, 1);


        Point2D i = Point2D.middle(a, b);

        assertEquals(1.0, i.x());
        assertEquals(1.0, i.y());
    }

}
