package io.github.purpleloop.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

/** Tests on text utilities. */
class TextUtilsTest {

    /** A sample text. */
    private static final String SAMPLE_TEXT1 = "This is a text that can be cutted into multiple lines.";
    
    /** A line length of 20. */
    private static final int LENGTH_20 = 20;

    /** Test for multi-lines - null. */
    @Test
    void testMultiLineNull() {
        assertNull(TextUtils.multiLine(null));
    }

    /** Test for multi-lines - empty. */
    @Test
    void testMultiLineEmpty() {
        assertEquals("", TextUtils.multiLine(""));
    }

    /** Test for multi-lines - Too short to cut. */
    @Test
    void testMultiLineTooShort() {
        assertEquals("Very short text.", TextUtils.multiLine("Very short text."));
    }

    /** Test for multi-lines - nominal. */
    @Test
    void testMultiLineSample() {

        // @formatter:off
        assertEquals("This is a text that can \\n"
                   + "be cutted into multiple \\n"
                   + "lines.",
                TextUtils.multiLine(SAMPLE_TEXT1, TextUtils.NEW_LINE, LENGTH_20));
        // @formatter:on
    }
    
    /** Test for get multi-lines - null. */
    @Test
    void testGetMultiLinesNull() {
        assertTrue(TextUtils.getMultiLines(null, LENGTH_20).isEmpty());
    }

    /** Test for get multi-lines - empty. */
    @Test
    void testGetMultiLinesEmpty() {
        assertTrue(TextUtils.getMultiLines("", LENGTH_20).isEmpty());
    }

    /** Test for get multi-lines - sample. */
    @Test
    void testGetMultiLinesSample() {
        List<String> multiLines = TextUtils.getMultiLines(SAMPLE_TEXT1, LENGTH_20);
        assertEquals(3, multiLines.size());
        assertEquals("This is a text that", multiLines.get(0));
        assertEquals("can be cutted into", multiLines.get(1));
        assertEquals("multiple lines.", multiLines.get(2));
    }
    
}

