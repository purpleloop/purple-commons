package io.github.purpleloop.commons.math.geom;

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

}
