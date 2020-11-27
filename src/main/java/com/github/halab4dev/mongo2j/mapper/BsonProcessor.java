package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonIgnore;
import com.github.halab4dev.mongo2j.annotation.BsonProperty;
import com.github.halab4dev.mongo2j.util.Validator;

import java.lang.reflect.Field;

/**
 * author halab
 */
public abstract class BsonProcessor {

    /**
     * Return true if field is annotated with @BsonProperty with isObjectId = true
     * @param field class attribute
     * @return true if field is annotated with @BsonProperty with isObjectId = true
     */
    protected boolean isAnnotatedObjectId(Field field) {
        if (!field.isAnnotationPresent(BsonProperty.class)) {
            return false;
        }
        BsonProperty annotation = field.getAnnotation(BsonProperty.class);
        return annotation.isObjectId();
    }

    /**
     * Return true if field is annotated with @BsonIgnore
     * @param field class attribute
     * @return true if field is annotated with @BsonIgnore
     */
    protected boolean isAnnotatedIgnored(Field field) {
        return field.isAnnotationPresent(BsonIgnore.class);
    }

    /**
     * Get mongo document field name
     *
     * @param field class attribute
     * @return mongo document field name
     */
    protected String getDocumentFieldName(Field field) {
        if (!field.isAnnotationPresent(BsonProperty.class)) {
            return field.getName();
        }
        BsonProperty annotation = field.getAnnotation(BsonProperty.class);
        String value = annotation.value();
        return Validator.isEmpty(value)  ? field.getName() : value;
    }
}
