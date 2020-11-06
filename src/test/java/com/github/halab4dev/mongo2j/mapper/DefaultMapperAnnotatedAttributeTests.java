package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * @author halab
 */
public class DefaultMapperAnnotatedAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with annotated attribute")
    public void testSerializeAnnotatedAttribute() {
        AnnotatedAttributeExample object =
                new AnnotatedAttributeExample("normal", "empty", "annotated");
        Document document = mapper.toDocument(object);

        assertEquals(object.getNormalAttribute(), document.getString("normalAttribute"));
        assertEquals(object.getEmptyAnnotatedAttribute(), document.getString("emptyAnnotatedAttribute"));
        assertEquals(object.getAnnotatedAttribute(), document.getString("annotated_attribute"));
    }


    @Test
    @DisplayName("Test deserialize with annotated attribute")
    public void testDeserializeComplexAttribute() {
        Document document = new Document("normalAttribute", "normal")
                .append("emptyAnnotatedAttribute", "empty")
                .append("annotated_attribute", "annotated");
        AnnotatedAttributeExample object = mapper.toObject(document, AnnotatedAttributeExample.class);

        assertEquals(document.getString("normalAttribute"), object.getNormalAttribute());
        assertEquals(document.getString("emptyAnnotatedAttribute"), object.getEmptyAnnotatedAttribute());
        assertEquals(document.getString("annotated_attribute"), object.getAnnotatedAttribute());
    }
}
