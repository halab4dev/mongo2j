package com.github.halab4dev.mongo2j.util;

import com.github.halab4dev.mongo2j.annotation.BsonProperty;

import java.lang.reflect.Field;

/*
 *
 * @author halab
 */
public class BsonUtils {

    private BsonUtils() {}

    /**
     * Get mongo document field name
     *
     * @param field class attribute
     * @return mongo document field name
     */
    public static String getDocumentFieldName(Field field) {
        if (!field.isAnnotationPresent(BsonProperty.class)) {
             return field.getName();
        }
        BsonProperty annotation = field.getAnnotation(BsonProperty.class);
        String value = annotation.value();
        return Validator.isEmpty(value)  ? field.getName() : value;
    }
}
