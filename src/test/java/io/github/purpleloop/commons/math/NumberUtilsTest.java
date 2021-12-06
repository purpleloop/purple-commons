package io.github.purpleloop.commons.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/** Tests on vectors. */
public class NumberUtilsTest {

    /** Tests equality - Ok case zero. */
    @Test
    public void testEqualsOkZero() {
        assertTrue(NumberUtils.equals(0.0, 0.0));
    }

    /** Tests equality - Ok case sample number. */
    @Test
    public void testEqualsOkSample() {
        assertTrue(NumberUtils.equals(123.45678, 123.45678));
    }

    /** Tests equality - Ok case sample negative number. */
    @Test
    public void testEqualsOkSampleNegative() {
        assertTrue(NumberUtils.equals(-123.45678, -123.45678));
    }

    /** Tests equality - Ko one is negative. */
    @Test
    public void testEqualsKoSampleOneNegative() {
        assertFalse(NumberUtils.equals(-123.45678, 123.45678));
    }

    /** Tests equality - Ko another one is negative. */
    @Test
    public void testEqualsKoSampleOtherNegative() {
        assertFalse(NumberUtils.equals(123.45678, -123.45678));
    }

    /** Tests equality - Ko numbers are different. */
    @Test
    public void testEqualsKoSampleDifferent() {
        assertFalse(NumberUtils.equals(456.789, -123.456));
    }

    /** Tests equality - Ok numbers are sufficiently close. */
    @Test
    public void testEqualsOkSampleClose() {
        assertTrue(NumberUtils.equals(0.9999999, 1.0));
    }

    /** Tests equality - Ko numbers are not so close. */
    @Test
    public void testEqualsKoSampleNotSoClose() {
        assertFalse(NumberUtils.equals(0.9999989, 1.0));
    }

}
