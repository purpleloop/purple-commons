package io.github.purpleloop.commons.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/** Utilities for texts. */
public final class TextUtils {

    /** A new line char (\n). */
    public static final String NEW_LINE = "\\n";

    /** A classical line length for cutting lines in multi-lines. */
    public static final int MULTILINE_LINE_DEFAULT_CUT_LENGTH_80 = 80;

    /**
     * String containing characters user as guides for cutting lines in
     * multi-lines.
     */
    public static final String MULTILINE_LINE_CUT_CHARS = ",;.:-";

    private TextUtils() {
        // This private constructor prevents instantiation of the utility class.
    }

    /**
     * Splits a single string on multiple lines.
     * 
     * Uses {@link TextUtils#MULTILINE_LINE_DEFAULT_CUT_LENGTH_80} and
     * {@link TextUtils#NEW_LINE}.
     * 
     * @param source Source string
     * @return mult-iline string
     */
    public static String multiLine(String source) {
        return multiLine(source, TextUtils.NEW_LINE,
                TextUtils.MULTILINE_LINE_DEFAULT_CUT_LENGTH_80);
    }

    /**
     * Splits a single string on multiple lines.
     * 
     * Uses {@link TextUtils#MULTILINE_LINE_CUT_CHARS}
     * 
     * @param source Source string
     * @param newLine separator to use
     * @param minLength
     * @return mult-iline string, null if source is null
     */
    public static String multiLine(String source, String newLine, int minLength) {

        if (source == null || source.isEmpty()) {
            return source;
        }

        StringBuilder multiLineBuffer = new StringBuilder();
        int sourceIndex = 0;
        int currentLineLength = 0;

        while (sourceIndex < source.length()) {

            char sourceChar = source.charAt(sourceIndex);
            multiLineBuffer.append(sourceChar);

            if ((currentLineLength >= minLength) && isMultiLineCutChar(sourceChar)) {
                multiLineBuffer.append(newLine);
                currentLineLength = 0;
            }
            sourceIndex++;
            currentLineLength++;
        }

        return multiLineBuffer.toString();
    }

    /**
     * @param ch char to be tested
     * @return true if the character can be user to cut a line
     */
    private static boolean isMultiLineCutChar(char ch) {
        return Character.isWhitespace(ch) || (MULTILINE_LINE_CUT_CHARS.indexOf(ch) != -1);
    }

    /**
     * Gets a list of lines from a source string.
     * 
     * @param sourceString the source string
     * @param limit line length limit
     * @return list of lines
     */
    public static List<String> getMultiLines(String sourceString, int limit) {

        if (sourceString == null || sourceString.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> resultLines = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(sourceString, " \t\r\n");

        StringBuilder buffer = new StringBuilder();
        String tok;
        while (tokenizer.hasMoreTokens()) {
            tok = tokenizer.nextToken();

            if ((buffer.length() + tok.length() + 1) > limit) {

                // Appending the token results in overflow, so flush buffer before.
                resultLines.add(buffer.toString().trim());
                buffer.setLength(0);
            } else {
                buffer.append(" ");
            }
            buffer.append(tok);
        }

        String remaining = buffer.toString().trim();

        if (!remaining.isEmpty()) {
            resultLines.add(remaining);
        }

        return resultLines;
    }

}
