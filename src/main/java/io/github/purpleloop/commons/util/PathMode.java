package io.github.purpleloop.commons.util;

/**
 * Enumeration of modes to handle file / resource paths lookup when resolving
 * file references.
 */
public enum PathMode {

    /** Path is absolute to the file system. */
    ABSOLUTE,

    /** Path is relative to the current file. */
    RELATIVE,

    /** Resource is in the class path. */
    CLASSPATH;

}
