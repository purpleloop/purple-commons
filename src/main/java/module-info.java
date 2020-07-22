module purpleloop.commons {
		
    exports purpleloop.commons.lang;
    exports purpleloop.commons.math;
    exports purpleloop.commons.swing;
    
    requires transitive java.desktop;
    requires commons.logging;    
}