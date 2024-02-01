package io.github.purpleloop.commons.ui;

/** Message type with different levels. */
public enum UiMessageType {

    /** An error message - To be typically displayed with an error dialog. */
    ERROR,

    /** A warning message - To be typically displayed with a warning dialog. */
    WARN,

    /** An informative message - To be typically in an info dialog. */
    INFO,

    /**
     * A simple trace message - To be typically logged without disturbing the
     * user.
     */
    TRACE;
}
