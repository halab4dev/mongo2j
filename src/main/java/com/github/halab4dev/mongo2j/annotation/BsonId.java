package com.github.halab4dev.mongo2j.annotation;

import org.bson.types.ObjectId;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 *
 * @author halab
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BsonId {

    Class<?> value() default ObjectId.class;
}
