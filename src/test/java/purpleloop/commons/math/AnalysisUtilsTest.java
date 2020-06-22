package purpleloop.commons.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Tests cases for analysis utilities. */
public class AnalysisUtilsTest {

    /** Tests the case maximal is lower than minimal. */
    @Test(expected = IllegalArgumentException.class)
    public void boundMaxLowerMin() {
        AnalysisUtils.bound(0, 1, 0);
    }

    /** Tests the case value equals to both bounds. */
    @Test
    public void boundValueBothBounds() {
        assertEquals(20, AnalysisUtils.bound(20, 20, 20));
    }

    /** Tests the case value is between bounds. */
    @Test
    public void boundValueInBounds() {
        assertEquals(20, AnalysisUtils.bound(20, 10, 30));
    }

    /** Tests the case value is equals to minimal. */
    @Test
    public void boundValueMinimal() {
        assertEquals(20, AnalysisUtils.bound(20, 20, 30));
    }

    /** Tests the case value is equals to maximal. */
    @Test
    public void boundValueMaximal() {
        assertEquals(30, AnalysisUtils.bound(30, 20, 30));
    }

    /** Tests the case value is lower. */
    @Test
    public void boundValueLower() {
        assertEquals(20, AnalysisUtils.bound(10, 20, 30));
    }

    /** Tests the case value is higher. */
    @Test
    public void boundValueHigher() {
        assertEquals(20, AnalysisUtils.bound(30, 10, 20));
    }

}
