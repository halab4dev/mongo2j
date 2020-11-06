package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * author halab
 */
public class DefaultMapperDateAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with date attribute")
    public void testSerializeDateAttribute() {
        Date date = new Date();
        DateAttributeExample object =                new DateAttributeExample("halab", date);

        Document document = mapper.toDocument(object);

        assertEquals(object.getName(), document.getString("name"));
        assertEquals(object.getBirthday(), document.getDate("birthday"));
    }

    @Test
    @DisplayName("Test deserialize with date attribute")
    public void testDeserializeDateAttribute() {
        Date date = new Date();

        Document document = new Document("name", "halab").append("birthday", date);
        DateAttributeExample object =  mapper.toObject(document, DateAttributeExample.class);

        assertEquals(document.getString("name"), object.getName());
        assertEquals(document.getDate("birthday"), object.getBirthday());
    }
}
