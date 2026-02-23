package io.github.purpleloop.commons.math.algebra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for the 2D int array. */
public class Int2DMatrixTest {

    // @formatter:off
    /** Test matrix. */
    private static final int[][] M = {
            { 1, 2, 3, 4 }, 
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 }
          };
    
    /** Test diagonal matrix. */
    private static final int[][] DIAGONAL = {
            { 1, 0, 0, 0 }, 
            { 0, 2, 0, 0 },
            { 0, 0, 3, 0 },
            { 0, 0, 0, 4 }
          };
    
    /** Test triangular matrix. */
    private static final int[][] TRIANGULAR = {
            { 1, 0, 0, 0 }, 
            { 5, 2, 0, 0 },
            { 2, 8, 3, 0 },
            { 9, 4, 7, 4 }
          };    
    
    // @formatter:on

    /** Tests a simple get and set. */
    @Test
    void testSimpleGetSet() {

        Int2DMatrix matrix = new Int2DMatrix(5, 2);
        matrix.set(4, 1, 25);
        assertEquals(25, matrix.get(4, 1));
    }

    /** Tests the initialization with a 2D array. */
    @Test
    void testInitFromArray2D() {

        Int2DMatrix matrix = new Int2DMatrix(M);
        assertEquals(4, matrix.getWidth());
        assertEquals(3, matrix.getHeight());
        assertEquals(3, matrix.get(2, 0));
        assertEquals(12, matrix.get(3, 2));
    }

    /** Tests the sum of rows. */
    @Test
    void testArraySumRow() {

        Int2DMatrix matrix = new Int2DMatrix(M);
        assertEquals(10, matrix.arraySumRow(0));
        assertEquals(26, matrix.arraySumRow(1));
        assertEquals(42, matrix.arraySumRow(2));
    }

    /** Tests the sum of columns. */
    @Test
    void testArraySumColumn() {

        Int2DMatrix matrix = new Int2DMatrix(M);
        assertEquals(15, matrix.arraySumColumn(0));
        assertEquals(18, matrix.arraySumColumn(1));
        assertEquals(21, matrix.arraySumColumn(2));
        assertEquals(24, matrix.arraySumColumn(3));
    }

    /** Tests a simple diagonal matrix - ok. */
    @Test
    void testDiagonalOk() {

        Int2DMatrix matrix = new Int2DMatrix(DIAGONAL);
        assertTrue(matrix.isSquare());
        assertTrue(matrix.isDiagonal());
    }
    
    /** Tests a simple diagonal matrix - ko not square. */
    @Test
    void testDiagonalKoNotSquare() {

        Int2DMatrix matrix = new Int2DMatrix(M);
        assertFalse(matrix.isSquare());
        assertFalse(matrix.isDiagonal());
    }


    /** Tests a simple diagonal matrix - ko value outside diagonal. */
    @Test
    void testDiagonalKoValuesOutside() {

        Int2DMatrix matrix = new Int2DMatrix(TRIANGULAR);
        assertTrue(matrix.isSquare());
        assertFalse(matrix.isDiagonal());
    }    
    
}
