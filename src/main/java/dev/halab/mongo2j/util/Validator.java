package dev.halab.mongo2j.util;

import java.util.Collection;
import java.util.Date;

/**
 * Provides methods to validate data
 *
 * @author Apollo
 */
public final class Validator {

    private Validator() {

    }

    /**
     * Return <i>true</i> if object is null
     *
     * @param object object
     * @return <i>true</i> if object is null
     */
    public static boolean isNull(Object object) {
        return object == null;
    }


    /**
     * Return <i>true</i> if collection is not empty
     *
     * @param collection collection
     * @return <i>true</i> if collection is not empty
     */
    public static boolean isNotEmpty(Collection collection) {
        return collection != null && !collection.isEmpty();
    }


    /**
     * Return <i>true</i> if class is valid mongo id type
     *
     * @param clazz class
     * @return <i>true</i> if class is valid mongo id type
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidIdType(Class clazz) {
        return clazz.isAssignableFrom(String.class)
                || clazz.isAssignableFrom(Date.class);
    }
}
