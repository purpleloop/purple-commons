module io.github.purpleloop.commons {

    exports io.github.purpleloop.commons.direction;
    exports io.github.purpleloop.commons.exception;
    exports io.github.purpleloop.commons.lang;
    exports io.github.purpleloop.commons.math;
    exports io.github.purpleloop.commons.math.geom;
    exports io.github.purpleloop.commons.ui;
    exports io.github.purpleloop.commons.util;
    exports io.github.purpleloop.commons.xml;

    requires commons.logging;
    requires transitive java.xml;
}