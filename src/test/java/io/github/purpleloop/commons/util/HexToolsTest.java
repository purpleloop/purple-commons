package io.github.purpleloop.commons.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/** Tests for hexadecimal tools. */
public class HexToolsTest {

    /** A sample buffer. */
    public static final byte[] BUFFER1 = { (byte) 0x2B };

    /** Another sample buffer. */
    public static final byte[] BUFFER2 = { (byte) 0xFF, 0x25, 0x01 };

    /** Tests integer conversion. */
    @Test
    void testToHexInt() {

        assertEquals("", HexTools.toHex(0, 0));
        assertEquals("", HexTools.toHex(1, 0));

        assertEquals("0", HexTools.toHex(0, 1));

        assertEquals("00", HexTools.toHex(0, 2));
        assertEquals("01", HexTools.toHex(1, 2));
        assertEquals("10", HexTools.toHex(16, 2));
        assertEquals("FF", HexTools.toHex(255, 2));
    }

    /** Tests long conversion. */
    @Test
    void testToHexLong() {

        assertEquals("", HexTools.toHex((long) 0, 0));
        assertEquals("", HexTools.toHex((long) 1, 0));

        assertEquals("0", HexTools.toHex((long) 0, 1));

        assertEquals("00", HexTools.toHex((long) 0, 2));
        assertEquals("01", HexTools.toHex((long) 1, 2));
        assertEquals("10", HexTools.toHex((long) 16, 2));
        assertEquals("FF", HexTools.toHex((long) 255, 2));
    }

    /** Tests hex buffer conversion. */
    @Test
    void toHexBuffer() {

        assertEquals("", HexTools.toHex(new byte[0]));
        assertEquals("2B", HexTools.toHex(BUFFER1));
        assertEquals("FF2501", HexTools.toHex(BUFFER2));

        assertEquals("", HexTools.toHex(new byte[0], 0, 0));

        assertEquals("", HexTools.toHex(BUFFER2, 0, 0));
        assertEquals("", HexTools.toHex(BUFFER2, 1, 0));
        assertEquals("FF2501", HexTools.toHex(BUFFER2, 0, 3));
        assertEquals("FF25", HexTools.toHex(BUFFER2, 0, 2));
        assertEquals("2501", HexTools.toHex(BUFFER2, 1, 2));
        assertEquals("25", HexTools.toHex(BUFFER2, 1, 1));
    }

    /**
     * Tests hex buffer conversion - Raises an exception because offset is
     * negative.
     */
    @Test
    void toHexBufferOffsetNegative() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            HexTools.toHex(BUFFER2, -1, 2);
        });

    }

    /**
     * Tests hex buffer conversion - Raises an exception because offset is beyond
     * limits of the buffer.
     */
    @Test
    void toHexBufferOffsetOverflow() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            HexTools.toHex(BUFFER2, 5, 2);
        });

    }

    /**
     * Tests hex buffer conversion - Raises an exception because requested length
     * goes beyond limits of the buffer.
     */
    @Test
    void toHexBufferLengthOverflow() {

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            HexTools.toHex(BUFFER2, 2, 5);
        });

    }

    /** Tests byte array to hexadecimal string conversion with separators. */
    @Test
    void testBufferToHexString() {

        assertEquals("FF2501", HexTools.bufferToHexString(BUFFER2, null));
        assertEquals("2B", HexTools.bufferToHexString(BUFFER1, null));
        assertEquals("2B", HexTools.bufferToHexString(BUFFER1, '.'));
        assertEquals("FF.25.01", HexTools.bufferToHexString(BUFFER2, '.'));
    }

    /** Tests hexadecimal string to byte array conversion. */
    @Test
    void testBufferFromHexString() {

        assertArrayEquals(BUFFER2, HexTools.bufferFromHexString("FF2501"));
        assertArrayEquals(BUFFER1, HexTools.bufferFromHexString("2B"));
    }

}
