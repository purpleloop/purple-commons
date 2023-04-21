package io.github.purpleloop.commons.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests on vectors. */
class NumberUtilsTest {

    /** Tests equality - Ok case zero. */
    @Test
    void testEqualsOkZero() {
        assertTrue(NumberUtils.equals(0.0, 0.0));
    }

    /** Tests equality - Ok case sample number. */
    @Test
    void testEqualsOkSample() {
        assertTrue(NumberUtils.equals(123.45678, 123.45678));
    }

    /** Tests equality - Ok case sample negative number. */
    @Test
    void testEqualsOkSampleNegative() {
        assertTrue(NumberUtils.equals(-123.45678, -123.45678));
    }

    /** Tests equality - Ko one is negative. */
    @Test
    void testEqualsKoSampleOneNegative() {
        assertFalse(NumberUtils.equals(-123.45678, 123.45678));
    }

    /** Tests equality - Ko another one is negative. */
    @Test
    void testEqualsKoSampleOtherNegative() {
        assertFalse(NumberUtils.equals(123.45678, -123.45678));
    }

    /** Tests equality - Ko numbers are different. */
    @Test
    void testEqualsKoSampleDifferent() {
        assertFalse(NumberUtils.equals(456.789, -123.456));
    }

    /** Tests equality - Ok numbers are sufficiently close. */
    @Test
    void testEqualsOkSampleClose() {
        assertTrue(NumberUtils.equals(0.9999999, 1.0));
    }

    /** Tests equality - Ko numbers are not so close. */
    @Test
    void testEqualsKoSampleNotSoClose() {
        assertFalse(NumberUtils.equals(0.9999989, 1.0));
    }

}
