package io.github.purpleloop.commons.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.github.purpleloop.commons.exception.PurpleException;

/** Utility class for reflexivity. */
public final class ReflexivityTools {

    /** Class logger. */
    private static Log log = LogFactory.getLog(ReflexivityTools.class);

    /** Private constructor. */
    private ReflexivityTools() {
    }

    /**
     * Creates an instance of a given class using provided parameters.
     * 
     * @param <T> the requested type of the new object
     * @param className the name of the class to instantiate
     * @param paramsClasses the classes of the parameters
     * @param paramsValues the values of the parameters
     * @return the newly created instance
     * @throws PurpleException in case of problem during instance creation
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String className, Class<?>[] paramsClasses,
            Object[] paramsValues) throws PurpleException {

        if (log.isDebugEnabled()) {
            log.debug("Instantiating a class '" + className + "'");

            for (int index = 0; index < paramsClasses.length; index++) {
                log.debug("Parameter[" + index + "] " + paramsClasses[index].getName() + " <- "
                        + paramsValues[index]);
            }
        }

        try {
            Class<T> requestedClass = (Class<T>) Class.forName(className);
            Constructor<T> constructor = requestedClass.getConstructor(paramsClasses);
            return constructor.newInstance(paramsValues);

        } catch (ClassNotFoundException e) {
            log.error("Instantiation failed, the class '" + className + "' is missing", e);
            throw new PurpleException("Missing class '" + className + "' while instantiating.");

        } catch (NoSuchMethodException e) {

            StringBuilder sb = new StringBuilder();

            if (paramsClasses.length > 0) {

                sb.append("Expected parameters : ");

                boolean separator = false;

                for (int i = 0; i < paramsClasses.length; i++) {

                    if (separator) {
                        sb.append(", ");
                    } else {
                        separator = true;
                    }

                    sb.append(paramsClasses[i].getCanonicalName());
                }
            }

            log.error("There is no constructor for the class " + className
                    + " with the provided arguments types. " + sb.toString(), e);
            throw new PurpleException("No matching constructor found for class " + className + ".");

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            log.error("Instantiation failed for class " + className, e);
            throw new PurpleException("Error while instantiating " + className + ".");
        }

    }

}
