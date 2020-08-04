package io.github.purpleloop.commons.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/** Tests for char arrays functions. */
public class CharArraysTest {

    /** A string. */
    private static final String SOMETHING = "something";

    /** A swapped string. */
    private static final String SNMETHIOG = "snmethiog";

    /** An string with ordered chars. */
    private static final String ORDERED_CHARS = "123ABCabcdefgh";

    /** Tests the swapping when array is null. */
    @Test(expected = NullPointerException.class)
    public void testSwapArrayNull() {
        CharArrays.swap(null, 0, 0);
    }

    /** Tests the swapping when array is empty. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSwapArrayEmpty() {
        CharArrays.swap(new char[0], 0, 0);
    }

    /** Tests the swapping when out of bounds 1. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSwapArrayOutObBounds() {
        CharArrays.swap("qwerty".toCharArray(), -5, 0);
    }

    /** Tests the swapping when out of bounds 2. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSwapArrayOutObBounds2() {
        CharArrays.swap("qwerty".toCharArray(), 0, -5);
    }

    /** Tests the swapping when out of bounds 3. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSwapArrayOutObBounds3() {
        CharArrays.swap("qwerty".toCharArray(), 10, 0);
    }

    /** Tests the swapping when out of bounds 4. */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSwapArrayOutObBounds4() {
        CharArrays.swap("qwerty".toCharArray(), 0, 10);
    }

    /** Tests the swapping without effect. */
    @Test
    public void testSwapArrayOutObBoundsNoEffect() {
        assertEquals(SOMETHING, String.valueOf(CharArrays.swap(SOMETHING.toCharArray(), 3, 3)));
    }

    /** Tests the swapping with effect. */
    @Test
    public void testSwapArrayOutObBoundsEffect() {
        assertEquals(SNMETHIOG, String.valueOf(CharArrays.swap(SOMETHING.toCharArray(), 1, 7)));
    }

    /** Tests the shuffle null array. */
    @Test(expected = NullPointerException.class)
    public void testShuffleNullArray() {
        CharArrays.shuffle(null);
    }

    /** Tests the shuffle empty array. */
    @Test
    public void testShuffleEmptyArray() {
        Assert.assertArrayEquals(new char[0], CharArrays.shuffle(new char[0]));
    }

    /**
     * Tests the shuffle of a non empty array. Shuffling an ordered thing gives
     * something different, that just need to be be reordered to be equal to
     * original.
     */
    @Test
    public void testShuffleNonEmptyArray() {
        char[] charArray = ORDERED_CHARS.toCharArray();
        CharArrays.shuffle(charArray);
        assertEquals(14, charArray.length);
        assertNotEquals(ORDERED_CHARS, String.valueOf(charArray));
        Arrays.sort(charArray);
        assertEquals(ORDERED_CHARS, String.valueOf(charArray));
    }

}
