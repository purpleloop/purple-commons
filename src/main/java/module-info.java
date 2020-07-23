module purpleloop.commons {

    exports purpleloop.commons.exception;
    exports purpleloop.commons.lang;
    exports purpleloop.commons.math;
    exports purpleloop.commons.xml;

    requires commons.logging;
    requires transitive java.xml;
}