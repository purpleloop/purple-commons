package io.github.purpleloop.commons.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Tests for the 2D byte array. */
class Byte2DMatrixTest {

	/** Sample data. */
    public static final byte[][] DATA = { { 45, 98 }, { 72, 34 }, { 19, 48 } };

    /** Tests that the array is initialized with zeroes. */
    @Test
    void testGetDefault() {

        Byte2DMatrix matrix = new Byte2DMatrix(2, 2);
        assertEquals(0, matrix.get(0, 0));
        assertEquals(0, matrix.get(1, 0));
        assertEquals(0, matrix.get(0, 1));
        assertEquals(0, matrix.get(1, 1));
    }

    /** Tests put ant get. */
    @Test
    void testGetPut() {

        Byte2DMatrix matrix = new Byte2DMatrix(2, 2);
        matrix.put(1, 0, (byte) 25);

        assertEquals(0, matrix.get(0, 0));
        assertEquals(25, matrix.get(1, 0));
        assertEquals(0, matrix.get(0, 1));
        assertEquals(0, matrix.get(1, 1));
    }

    /** Tests the filling. */
    @Test
    void testFill() {

        Byte2DMatrix matrix = new Byte2DMatrix(3, 2, DATA);

        assertEquals(45, matrix.get(0, 0));
        assertEquals(72, matrix.get(1, 0));
        assertEquals(19, matrix.get(2, 0));
        assertEquals(98, matrix.get(0, 1));
        assertEquals(34, matrix.get(1, 1));
        assertEquals(48, matrix.get(2, 1));
    }

    /** Test of and operator. */
    @Test
    void testAnd() {

        Byte2DMatrix matrix = new Byte2DMatrix(1, 1);
        matrix.put(0, 0, (byte) 15);
        matrix.and(0, 0, (byte) 11);
        assertEquals(11, matrix.get(0, 0));

        matrix.and(0, 0, (byte) 14);
        assertEquals(10, matrix.get(0, 0));

        matrix.and(0, 0, (byte) 13);
        assertEquals(8, matrix.get(0, 0));

        matrix.and(0, 0, (byte) 7);
        assertEquals(0, matrix.get(0, 0));
    }

    /** Test of or operator. */
    @Test
    void testOr() {

        Byte2DMatrix matrix = new Byte2DMatrix(1, 1);
        matrix.put(0, 0, (byte) 0);
        matrix.or(0, 0, (byte) 1);
        assertEquals(1, matrix.get(0, 0));

        matrix.or(0, 0, (byte) 2);
        assertEquals(3, matrix.get(0, 0));

        matrix.or(0, 0, (byte) 4);
        assertEquals(7, matrix.get(0, 0));

        matrix.or(0, 0, (byte) 8);
        assertEquals(15, matrix.get(0, 0));
    }

    /** Tests of the hexadecimal dump. */
    @Test
    void testHexDump() {

        Byte2DMatrix matrix = new Byte2DMatrix(3, 2, DATA);

        String actual = matrix.hexDump();
        assertEquals("2D 48 13\n62 22 30\n", actual);
    }
}
