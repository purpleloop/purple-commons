package io.github.purpleloop.commons.math.geom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

/** Tests on linear equations. */
class LinearEquationTest {

    @Test
    void testFromPointO() {
         Optional<LinearEquation> equationOpt = LinearEquation.fromPoints(0, 0, 0, 0);
         assertFalse(equationOpt.isPresent());
    }
    
    @Test
    void testOnTheLineFirstPoint() {
        LinearEquation equation = LinearEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(0, 2, 0.0001));
    }

    @Test
    void testOnTheLineSecondPoint() {
        LinearEquation equation = LinearEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(4, 0, 0.0001));
    }    
    
    @Test
    void testOnTheLineSimple() {
        LinearEquation equation = LinearEquation.fromPoints(0, 2, 4, 0).get();
        assertTrue(equation.isOnTheline(2, 1, 0.0001));
    }
    
    @Test
    void testOnTheLineVertial() {
        LinearEquation equation = LinearEquation.fromPoints(3, 3, 3, 2).get();
        assertTrue(equation.isOnTheline(3, 3, 0.0001));
    }    

    @Test
    void testOnTheLineVertialAside() {
        LinearEquation equation = LinearEquation.fromPoints(3, 3, 3, 2).get();
        assertFalse(equation.isOnTheline(4, 3, 0.0001));
    }     
    
    @Test
    void testOnTheLineFalse() {
        LinearEquation equation = LinearEquation.fromPoints(0, 2, 4, 0).get();
        assertFalse(equation.isOnTheline(3, 2, 0.0001));
    }

}
