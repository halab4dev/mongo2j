package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonProperty;

/*
 *
 * @author halab
 */
public class AnnotatedAttributeExample {

    private String normalAttribute;

    @BsonProperty
    private String emptyAnnotatedAttribute;

    @BsonProperty("annotated_attribute")
    private String annotatedAttribute;

    public AnnotatedAttributeExample() {
    }

    public AnnotatedAttributeExample(String normalAttribute, String emptyAnnotatedAttribute, String annotatedAttribute) {
        this.normalAttribute = normalAttribute;
        this.emptyAnnotatedAttribute = emptyAnnotatedAttribute;
        this.annotatedAttribute = annotatedAttribute;
    }

    public String getNormalAttribute() {
        return normalAttribute;
    }

    public void setNormalAttribute(String normalAttribute) {
        this.normalAttribute = normalAttribute;
    }

    public String getEmptyAnnotatedAttribute() {
        return emptyAnnotatedAttribute;
    }

    public void setEmptyAnnotatedAttribute(String emptyAnnotatedAttribute) {
        this.emptyAnnotatedAttribute = emptyAnnotatedAttribute;
    }

    public String getAnnotatedAttribute() {
        return annotatedAttribute;
    }

    public void setAnnotatedAttribute(String annotatedAttribute) {
        this.annotatedAttribute = annotatedAttribute;
    }
}
