package io.github.purpleloop.commons.math.geom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/** Tests on Cartesian equations of line. */
class CartesianLineEquationTest {

    /** Precision value. */
    private static final double EPSILON = 0.0001;

    @Test
    void testFromPointO() {
        Optional<CartesianLineEquation> equationOpt = CartesianLineEquation.fromPoints(0, 0, 0, 0);
        assertFalse(equationOpt.isPresent());
    }

    @Test
    void testOnTheLineFirstPoint() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(0, 2, EPSILON));
    }

    @Test
    void testOnTheLineSecondPoint() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(4, 0, EPSILON));
    }

    @Test
    void testOnTheLineSimple() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(2, 1, EPSILON));
    }

    @Test
    void testOnTheLineVertial() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(3, 3, 3, 2).get();
        assertTrue(equation.isOnTheline(3, 3, EPSILON));
    }

    @Test
    void testOnTheLineVertialAside() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(3, 3, 3, 2).get();
        assertFalse(equation.isOnTheline(4, 3, EPSILON));
    }

    @Test
    void testOnTheLineFalse() {
        CartesianLineEquation equation = CartesianLineEquation.fromPoints(0, 2, 4, 0).get();
        assertFalse(equation.isOnTheline(3, 2, EPSILON));
    }

    @Test
    void testPointOnBisector() {
        CartesianLineEquation equation = CartesianLineEquation.bisectorForSegment(3, 0, 2, 4).get();
        assertTrue(equation.isOnTheline(4.5, 2.5, EPSILON));
    }

    @Test
    void testCrossingSinglePoint() {
        CartesianLineEquation equation1 = new CartesianLineEquation(3, -2, 8);
        CartesianLineEquation equation2 = new CartesianLineEquation(5, 4, 6);

        Optional<Point2D> solution = CartesianLineEquation.intersection(equation1, equation2);

        assertTrue(solution.isPresent());
        Point2D point = solution.get();
        assertEquals(-2.0, point.x(), 0.001);
        assertEquals(1.0, point.y(), 0.001);
    }

    @Test
    void testCrossingSinglePoint2() {
        // y = 1
        CartesianLineEquation equation1 = new CartesianLineEquation(0, 1, -1);

        // x = 1
        CartesianLineEquation equation2 = new CartesianLineEquation(1, 0, -1);

        Optional<Point2D> solution = CartesianLineEquation.intersection(equation1, equation2);

        assertTrue(solution.isPresent());
        Point2D point = solution.get();
        assertEquals(1.0, point.x(), 0.001);
        assertEquals(1.0, point.y(), 0.001);
    }

    @Test
    void testCrossingSinglePointHorVer() {
        CartesianLineEquation equation1 = new CartesianLineEquation(2, -3, 4);
        CartesianLineEquation equation2 = new CartesianLineEquation(5, 2, -9);

        Optional<Point2D> solution = CartesianLineEquation.intersection(equation1, equation2);

        assertTrue(solution.isPresent());
        Point2D point = solution.get();
        assertEquals(1.0, point.x(), 0.001);
        assertEquals(2.0, point.y(), 0.001);
    }

    @Test
    void testCrossingNone() {
        // y=x
        CartesianLineEquation equation1 = new CartesianLineEquation(-1, 1, 0);

        // y=x+1
        CartesianLineEquation equation2 = new CartesianLineEquation(-1, 1, -1);

        Optional<Point2D> solution = CartesianLineEquation.intersection(equation1, equation2);

        assertTrue(solution.isEmpty());
    }

    @Test
    void testCrossingSame() {
        // y=x
        CartesianLineEquation equation1 = new CartesianLineEquation(-1, 1, 0);

        // y=x+1
        CartesianLineEquation equation2 = new CartesianLineEquation(-2, 2, -2);

        Optional<Point2D> solution = CartesianLineEquation.intersection(equation1, equation2);

        assertTrue(solution.isEmpty());
    }

    @Test
    void testIntersectionFirstBissectriceSegmentSinglePoint() {

        CartesianLineEquation line = new CartesianLineEquation(-1, 1, 0);

        Point2D a = new Point2D(0, 2);
        Point2D b = new Point2D(2, 0);
        Segment2D segment = new Segment2D(a, b);

        Optional<Point2D> solution = CartesianLineEquation.intersection(line, segment);

        assertTrue(solution.isPresent());
        Point2D point = solution.get();
        assertEquals(1.0, point.x(), 0.001);
        assertEquals(1.0, point.y(), 0.001);
    }

    @Test
    void testIntersectionOutsideSegment() {

        CartesianLineEquation line = new CartesianLineEquation(1, 0, 1);

        Point2D a = new Point2D(0, 2);
        Point2D b = new Point2D(2, 0);
        Segment2D segment = new Segment2D(a, b);

        Optional<Point2D> solution = CartesianLineEquation.intersection(line, segment);

        assertTrue(solution.isEmpty());
    }

    @Test
    void testIntersectionLineParellelToSegment() {

        CartesianLineEquation line = new CartesianLineEquation(-1, 1, 0);

        Point2D a = new Point2D(0, 2);
        Point2D b = new Point2D(1, 3);
        Segment2D segment = new Segment2D(a, b);

        Optional<Point2D> solution = CartesianLineEquation.intersection(line, segment);

        assertTrue(solution.isEmpty());
    }
}
