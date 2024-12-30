package io.github.purpleloop.commons.lang;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests on the RecursiveArray class. */
class RecursiveArrayTest {

    /** Tests the storage and retrieval of integers in a 2 x 3 x 4 array. */
    @Test
    void testStoreAndRetrieve() {

        List<Integer> dimensions = Arrays.asList(new Integer[] { 2, 3, 4 });
        List<Integer> coordinates = Arrays.asList(new Integer[] { 0, 0, 0 });
        int value = 0;
        int x, y, z;

        RecursiveArray recArray = new RecursiveArray(dimensions);
        for (x = 0; x < 2; x++) {
            coordinates.set(0, x);
            for (y = 0; y < 3; y++) {
                coordinates.set(1, y);
                for (z = 0; z < 4; z++) {
                    coordinates.set(2, z);
                    recArray.setInt(value++, coordinates);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (z = 0; z < 4; z++) {
            coordinates.set(2, z);
            for (y = 0; y < 3; y++) {
                coordinates.set(1, y);
                for (x = 0; x < 2; x++) {
                    coordinates.set(0, x);
                    sb.append(recArray.getInt(coordinates) + " ");
                }
            }
        }

        assertEquals("0 12 4 16 8 20 1 13 5 17 9 21 2 14 6 18 10 22 3 15 7 19 11 23 ",
                sb.toString());
    }

}
