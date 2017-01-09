package dev.halab.mongo2j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a class attribute to map it with specific field of mongo document
 *
 * @author Apollo
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DocumentField {

    /**
     * Name of mongo document field
     *
     * @return name of mongo document field
     */
    String name();
}
