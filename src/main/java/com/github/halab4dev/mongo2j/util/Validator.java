package com.github.halab4dev.mongo2j.util;

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
     * Return <i>true</i> if object is not null
     *
     * @param object object
     * @return <i>true</i> if object is not null
     */
    public static boolean isNotNull(Object object) {
        return object != null;
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
