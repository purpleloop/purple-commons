package io.github.purpleloop.commons.directions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.purpleloop.commons.direction.Direction;
import io.github.purpleloop.commons.direction.Direction4;

/** Unit tests for Direction4. */
class Direction4Test {

    @Test
    void testNext() {

        assertEquals(Direction4.WEST, Direction4.NORTH.next(true));
        assertEquals(Direction4.SOUTH, Direction4.WEST.next(true));
        assertEquals(Direction4.EAST, Direction4.SOUTH.next(true));
        assertEquals(Direction4.NORTH, Direction4.EAST.next(true));

        assertEquals(Direction4.WEST, Direction4.NORTH.next(1));
        assertEquals(Direction4.SOUTH, Direction4.WEST.next(1));
        assertEquals(Direction4.EAST, Direction4.SOUTH.next(1));
        assertEquals(Direction4.NORTH, Direction4.EAST.next(1));

        assertEquals(Direction4.NORTH, Direction4.NORTH.next(4));
        assertEquals(Direction4.NORTH, Direction4.NORTH.next(-4));

        assertEquals(Direction4.EAST, Direction4.NORTH.next(false));
        assertEquals(Direction4.NORTH, Direction4.WEST.next(false));
        assertEquals(Direction4.WEST, Direction4.SOUTH.next(false));
        assertEquals(Direction4.SOUTH, Direction4.EAST.next(false));

        assertEquals(Direction4.EAST, Direction4.NORTH.next(-1));
        assertEquals(Direction4.NORTH, Direction4.WEST.next(-1));
        assertEquals(Direction4.WEST, Direction4.SOUTH.next(-1));
        assertEquals(Direction4.SOUTH, Direction4.EAST.next(-1));
    }

    @Test
    void testValues() {

        List<Direction> dirs = Direction4.values();

        assertEquals(4, dirs.size());
        assertEquals(true, dirs.contains(Direction4.NORTH));
        assertEquals(true, dirs.contains(Direction4.SOUTH));
        assertEquals(true, dirs.contains(Direction4.EAST));
        assertEquals(true, dirs.contains(Direction4.WEST));

    }

    @Test
    void testAngles() {
        assertEquals(0.0, Direction4.EAST.getAngle(), 0.001);
        assertEquals(Math.PI / 2.0, Direction4.NORTH.getAngle(), 0.001);
        assertEquals(Math.PI, Direction4.WEST.getAngle(), 0.001);
        assertEquals(Math.PI * 3.0 / 2.0, Direction4.SOUTH.getAngle(), 0.001);
    }
}
