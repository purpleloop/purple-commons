package io.github.purpleloop.commons.util;

/** Hexadecimal tools. */
public final class HexTools {

    /** 4 high bits mask. */
    private static final int HI_MASK = 0xF0;

    /** 4 low bits mask. */
    private static final int LO_MASK = 0x0F;

    /** Hexadecimal base. */
    public static final int HEXA_BASE = 16;

    /** Hexadecimal chars string in upper case. */
    public static final String HEXA = "0123456789ABCDEF";

    /** Private constructor. */
    private HexTools() {
    }

    /**
     * Converts a byte value to a hexadecimal string.
     * 
     * @param value byte value to convert
     * @return hexadecimal string
     */
    public static String toHex(byte value) {

        int hi = (value & HI_MASK) >> 4;
        int lo = (value & LO_MASK);

        StringBuilder hexBuilder = new StringBuilder();
        hexBuilder.append(HEXA.charAt(hi));
        hexBuilder.append(HEXA.charAt(lo));
        return hexBuilder.toString();
    }

    /**
     * Converts an integer value to a hexadecimal string.
     * 
     * @param value integer value to convert
     * @param digits number of requested digits in the result
     * @return hexadecimal string
     */
    public static String toHex(int value, int digits) {

        StringBuilder hexBuilder = new StringBuilder();
        int remainingValue = value;

        for (int hexDigit = 0; hexDigit < digits; hexDigit++) {
            hexBuilder.insert(0, HEXA.charAt(remainingValue % HEXA_BASE));
            remainingValue = remainingValue / HEXA_BASE;
        }

        return hexBuilder.toString();
    }

    /**
     * Converts a long value to a hexadecimal string.
     * 
     * @param value long value to convert
     * @param digits number of requested digits in the result
     * @return hexadecimal string
     */
    public static String toHex(long value, int digits) {

        StringBuilder hexBuilder = new StringBuilder();
        long remainingValue = value;

        for (int hexDigit = 0; hexDigit < digits; hexDigit++) {
            hexBuilder.insert(0, HEXA.charAt((int) (remainingValue % HEXA_BASE)));
            remainingValue = remainingValue / HEXA_BASE;
        }

        return hexBuilder.toString();
    }

    /**
     * Converts a byte buffer to an hexadecimal string.
     * 
     * @param buffer byte buffer to convert
     * @return hexadecimal string
     */
    public static String toHex(byte[] buffer) {
        StringBuilder hexBuilder = new StringBuilder();
        for (int byteIndex = 0; byteIndex < buffer.length; byteIndex++) {
            hexBuilder.append(toHex(buffer[byteIndex]));
        }

        return hexBuilder.toString();
    }

    /**
     * Converts a byte buffer to an hexadecimal string.
     * 
     * @param buffer byte buffer to convert
     * @param offset offset where to start in the buffer
     * @param length number of bytes to convert
     * @return hexadecimal string
     */
    public static String toHex(byte[] buffer, int offset, int length) {

        int maxOffset = buffer.length;

        if (maxOffset == 0) {
            return "";
        }

        if (offset < 0 || offset >= maxOffset) {
            throw new ArrayIndexOutOfBoundsException(
                    "The offset " + offset + " is not between 0 and " + maxOffset);
        }

        if (offset + length > maxOffset) {
            throw new ArrayIndexOutOfBoundsException("Out of bounds : " + offset + "+" + length
                    + " is not between 0 and " + maxOffset);
        }

        StringBuilder hexBuilder = new StringBuilder();

        int remainingLength = length;

        int currentOffset = offset;

        while ((remainingLength > 0) && (currentOffset < maxOffset)) {

            hexBuilder.append(toHex(buffer[currentOffset]));
            remainingLength--;
            currentOffset++;
        }

        return hexBuilder.toString();
    }

    /**
     * Converts a byte buffer to an hexadecimal string whith a separator.
     * 
     * @param buffer byte buffer to convert
     * @param separator separator char (ignored if null)
     * @return hexadecimal string
     */
    public static String bufferToHexString(byte[] buffer, Character separator) {
        StringBuffer hexBuilder = new StringBuffer();
        boolean alreadySetADigit = false;

        for (int currentOffset = 0; currentOffset < buffer.length; currentOffset++) {

            if (separator != null) {

                if (alreadySetADigit) {
                    hexBuilder.append(separator);
                } else {
                    alreadySetADigit = true;
                }
            }

            hexBuilder.append(toHex(buffer[currentOffset]));
        }
        return hexBuilder.toString();
    }

    /**
     * Converts an hexadecimal string to a byte buffer.
     * 
     * @param hexStr The hexadecimal string to convert
     * @return the resulting byte buffer
     */
    public static byte[] bufferFromHexString(String hexStr) {

        String uppercasedHexString = hexStr.toUpperCase();

        byte[] byteBuffer = new byte[uppercasedHexString.length() / 2];

        for (int currentOffset = 0; currentOffset < byteBuffer.length; currentOffset++) {
            int hiValue = HEXA.indexOf(uppercasedHexString.charAt(2 * currentOffset));
            int loValue = HEXA.indexOf(uppercasedHexString.charAt(2 * currentOffset + 1));
            byteBuffer[currentOffset] = (byte) (((hiValue & LO_MASK) << 4) | (loValue & LO_MASK));
        }
        return byteBuffer;
    }

}
