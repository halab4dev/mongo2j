package dev.halab.mongo2j.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Provides methods to work with class
 *
 * @author Apollo
 */
public final class ClassUtils {

    private ClassUtils() {

    }

    private static final List<Class<?>> SIMPLE_VALUE_CLASSES = new ArrayList<>();

    static {
        SIMPLE_VALUE_CLASSES.add(String.class);
        SIMPLE_VALUE_CLASSES.add(Boolean.class);
        SIMPLE_VALUE_CLASSES.add(Character.class);
        SIMPLE_VALUE_CLASSES.add(Byte.class);
        SIMPLE_VALUE_CLASSES.add(Short.class);
        SIMPLE_VALUE_CLASSES.add(Integer.class);
        SIMPLE_VALUE_CLASSES.add(Long.class);
        SIMPLE_VALUE_CLASSES.add(Float.class);
        SIMPLE_VALUE_CLASSES.add(Double.class);
    }


    /**
     * Return <i>true</i> if field is primitive type or wrapped class
     *
     * @param field class attribute
     * @return <i>true</i> if field is primitive type or wrapped class
     */
    public static boolean isSimpleValue(Field field) {
        Class fieldClass = field.getType();
        return fieldClass.isPrimitive() || SIMPLE_VALUE_CLASSES.contains(fieldClass);
    }


    /**
     * Return <i>true</i> if field is a collection
     *
     * @param field class attribute
     * @return <i>true</i> if field is a collection
     */
    public static boolean isCollection(Field field) {
        return Collection.class.isAssignableFrom(field.getType());
    }


    /**
     * Return <i>true</i> if field is a map
     *
     * @param field class attribute
     * @return <i>true</i> if field is a map
     */
    public static boolean isMap(Field field) {
        return Map.class.isAssignableFrom(field.getType());
    }


    /**
     * Return <i>true</i> if object class is wrapped class
     *
     * @param object object
     * @return <i>true</i> if object class is wrapped class
     */
    public static boolean isWrappedClass(Object object) {
        return SIMPLE_VALUE_CLASSES.contains(object.getClass());
    }


    /**
     * Return <i>true</i> if class is wrapped class
     *
     * @param clazz class
     * @return <i>true</i> if class is wrapped class
     */
    public static boolean isWrappedClass(Class clazz) {
        return SIMPLE_VALUE_CLASSES.contains(clazz);
    }
}
