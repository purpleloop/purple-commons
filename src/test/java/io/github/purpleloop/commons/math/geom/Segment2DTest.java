package io.github.purpleloop.commons.math.geom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Tests for segments. */
class Segment2DTest {

    @Test
    void segmentContainsItsMiddle() {
        Point2D a = new Point2D(1, 1);
        Point2D b = new Point2D(3, 2);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = segment.middle();
        assertTrue(segment.contains(m));
    }
    
    @Test
    void generalSegmentContainsPointOnIt() {
        Point2D a = new Point2D(1, 3);
        Point2D b = new Point2D(5, 5);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = new Point2D(4, 4.5);
        assertTrue(segment.contains(m));
    }    
    
    @Test
    void verticalSegmentContainsPointOnIt() {
        Point2D a = new Point2D(4, -1);
        Point2D b = new Point2D(4, 2);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = new Point2D(4, 0);
        assertTrue(segment.contains(m));
    }
    
    @Test
    void segmentDoesNotContainanyDummyPoint() {
        Point2D a = new Point2D(1, 1);
        Point2D b = new Point2D(3, 2);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = new Point2D(10, 7);
        assertFalse(segment.contains(m));
    }

    @Test
    void generalSegmentDoesNoContainPointCloseToIt() {
        Point2D a = new Point2D(1, 3);
        Point2D b = new Point2D(5, 5);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = new Point2D(4, 4);
        assertFalse(segment.contains(m));
    }  
    
    @Test
    void segmentDoesNotContainanPointOnItsLineOutside() {
        Point2D a = new Point2D(2, 6);
        Point2D b = new Point2D(8, 4);
        Segment2D segment = new Segment2D(a, b);

        Point2D m = new Point2D(-1, 7);
        assertFalse(segment.contains(m));
    }
    
    @Test
    void segmentDoesNotContainanPointOnItsLineOutside2() {
        Point2D a = new Point2D(2, 6);
        Point2D b = new Point2D(8, 4);
        Segment2D segment = new Segment2D(a, b);
        
        Point2D m2 = new Point2D(11, 3);
        assertFalse(segment.contains(m2));        
    }    
    
    @Test
    void singlePointSegmentContainsItself() {
        Point2D o = new Point2D(0, 0);
        Segment2D segment = new Segment2D(o, o);

        assertTrue(segment.contains(o));
    }

    @Test
    void singlePointSegmentDoesNotContainsOther() {
        Point2D o = new Point2D(0, 0);
        Point2D a = new Point2D(0, 1);
        Point2D b = new Point2D(1, 1);
        Point2D c = new Point2D(1, 0);
        Segment2D segment = new Segment2D(o, o);

        assertFalse(segment.contains(a));
        assertFalse(segment.contains(b));
        assertFalse(segment.contains(c));
    }

}
