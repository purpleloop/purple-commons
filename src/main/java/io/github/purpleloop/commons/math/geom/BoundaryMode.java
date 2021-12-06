package io.github.purpleloop.commons.math.geom;

/** Boundary mode, when the vector is boxed. */
public enum BoundaryMode {

    /**
     * If a coordinate become out of bounds, is is translated to the opposite.
     */
    TRANSLATE,

    /** If a coordinate become out of bounds, is bounces on the border. */
    BOUNCE;

}
