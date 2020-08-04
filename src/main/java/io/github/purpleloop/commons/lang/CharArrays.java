package io.github.purpleloop.commons.lang;

import java.util.Random;

/** Utility functions for char arrays. */
public final class CharArrays {

    /** Dummy constructor for final class. */
    private CharArrays() {
    }

    /**
     * Swap two chars of a char array given by their respective positions.
     * 
     * The function may rise an ArrayIndexOutOfBoundsException for unexpected
     * arguments.
     * 
     * Warning: the array given in parameters is modified by the function.
     * 
     * @param charArray the char array, must not be null
     * @param a first position in array
     * @param b second position in array
     * @return the char array (useful for chaining)
     */
    public static char[] swap(char[] charArray, int a, int b) {

        int len = charArray.length;
        if (a < 0 || a >= len) {
            throw new ArrayIndexOutOfBoundsException(
                    "Index " + a + " is out of bounds [0, " + len + "]");
        }
        if (b < 0 || b >= len) {
            throw new ArrayIndexOutOfBoundsException(
                    "Index " + b + " is out of bounds [0, " + len + "]");
        }

        if (a == b) {
            return charArray;
        }

        return swapNoChecks(charArray, a, b);
    }

    /**
     * Swap two chars of a char array given by their respective positions.
     * 
     * Warnings: the array given in parameters is modified by the function. This
     * function does the swap without any checks.
     * 
     * @param charArray the char array, must not be null
     * @param a first position in array
     * @param b second position in array
     * @return the char array (useful for chaining)
     */
    public static char[] swapNoChecks(char[] charArray, int a, int b) {
        char swapped = charArray[a];
        charArray[a] = charArray[b];
        charArray[b] = swapped;
        return charArray;
    }

    /**
     * Randomly shuffles the array of chars.
     * 
     * The shuffling is similar to the one used in
     * {@link java.util.Collections#shuffle(java.util.List)}.
     * 
     * Warning: the array given in parameters is modified by the function.
     * 
     * @param charArray the char array, must not be null
     * @return the char array (useful for chaining)
     */
    public static char[] shuffle(char[] charArray) {
        return shuffle(charArray, Randoms.getRandom());
    }

    /**
     * Randomly shuffles the array of chars.
     * 
     * The shuffling is similar to the one used in
     * {@link java.util.Collections#shuffle(java.util.List)}.
     * 
     * Warning: the array given in parameters is modified by the function.
     * 
     * @param random random generator to use
     * @param charArray the char array, must not be null
     * @return the char array (useful for chaining)
     */
    public static char[] shuffle(char[] charArray, Random random) {
        for (int index = charArray.length; index > 1; index--) {
            swapNoChecks(charArray, index - 1, random.nextInt(index));
        }
        return charArray;
    }

}
