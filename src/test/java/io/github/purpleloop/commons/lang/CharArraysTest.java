package io.github.purpleloop.commons.lang;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/** Tests for char arrays functions. */
class CharArraysTest {

    /** A dummy array of chars. */
    private static final char[] QUERTY_ARRAY = "qwerty".toCharArray();

    /** A string. */
    private static final String SOMETHING = "something";

    /** A swapped string. */
    private static final String SNMETHIOG = "snmethiog";

    /** An string with ordered chars. */
    private static final String ORDERED_CHARS = "123ABCabcdefgh";

    /** Tests the swapping when array is null. */
    @Test
    void testSwapArrayNull() {

        assertThrows(NullPointerException.class, () -> {
            CharArrays.swap(null, 0, 0);
        });

    }

    /** Tests the swapping when array is empty. */
    @Test
    void testSwapArrayEmpty() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CharArrays.swap(new char[0], 0, 0);
        });
    }

    /** Tests the swapping when out of bounds 1. */
    @Test
    void testSwapArrayOutObBounds() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CharArrays.swap(QUERTY_ARRAY, -5, 0);
        });

    }

    /** Tests the swapping when out of bounds 2. */
    @Test
    void testSwapArrayOutObBounds2() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CharArrays.swap(QUERTY_ARRAY, 0, -5);
        });

    }

    /** Tests the swapping when out of bounds 3. */
    @Test
    void testSwapArrayOutObBounds3() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CharArrays.swap(QUERTY_ARRAY, 10, 0);
        });

    }

    /** Tests the swapping when out of bounds 4. */
    @Test
    void testSwapArrayOutObBounds4() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            CharArrays.swap(QUERTY_ARRAY, 0, 10);
        });

    }

    /** Tests the swapping without effect. */
    @Test
    void testSwapArrayOutObBoundsNoEffect() {
        assertEquals(SOMETHING, String.valueOf(CharArrays.swap(SOMETHING.toCharArray(), 3, 3)));
    }

    /** Tests the swapping with effect. */
    @Test
    void testSwapArrayOutObBoundsEffect() {
        assertEquals(SNMETHIOG, String.valueOf(CharArrays.swap(SOMETHING.toCharArray(), 1, 7)));
    }

    /** Tests the shuffle null array. */
    @Test
    void testShuffleNullArray() {

        assertThrows(NullPointerException.class, () -> {
            CharArrays.shuffle(null);
        });

    }

    /** Tests the shuffle empty array. */
    @Test
    void testShuffleEmptyArray() {
        assertArrayEquals(new char[0], CharArrays.shuffle(new char[0]));
    }

    /**
     * Tests the shuffle of a non empty array. Shuffling an ordered thing gives
     * something different, that just need to be be reordered to be equal to
     * original.
     */
    @Test
    void testShuffleNonEmptyArray() {
        char[] charArray = ORDERED_CHARS.toCharArray();
        CharArrays.shuffle(charArray);
        assertEquals(14, charArray.length);
        assertNotEquals(ORDERED_CHARS, String.valueOf(charArray));
        Arrays.sort(charArray);
        assertEquals(ORDERED_CHARS, String.valueOf(charArray));
    }

}
