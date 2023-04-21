package io.github.purpleloop.commons.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** Tests cases for analysis utilities. */
class AnalysisUtilsTest {

    /** Tests the case maximal is lower than minimal. */
    @Test
    void boundMaxLowerMin() {
        assertThrows(IllegalArgumentException.class, () -> {
            AnalysisUtils.bound(0, 1, 0);
        });
    }

    /** Tests the case value equals to both bounds. */
    @Test
    void boundValueBothBounds() {
        assertEquals(20, AnalysisUtils.bound(20, 20, 20));
    }

    /** Tests the case value is between bounds. */
    @Test
    void boundValueInBounds() {
        assertEquals(20, AnalysisUtils.bound(20, 10, 30));
    }

    /** Tests the case value is equals to minimal. */
    @Test
    void boundValueMinimal() {
        assertEquals(20, AnalysisUtils.bound(20, 20, 30));
    }

    /** Tests the case value is equals to maximal. */
    @Test
    void boundValueMaximal() {
        assertEquals(30, AnalysisUtils.bound(30, 20, 30));
    }

    /** Tests the case value is lower. */
    @Test
    void boundValueLower() {
        assertEquals(20, AnalysisUtils.bound(10, 20, 30));
    }

    /** Tests the case value is higher. */
    @Test
    void boundValueHigher() {
        assertEquals(20, AnalysisUtils.bound(30, 10, 20));
    }

}
