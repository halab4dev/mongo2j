package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * author halab
 */
public class DefaultMapperIgnoredAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with ignored attributes")
    public void testSerializeIgnoredAttributes() {
        IgnoredAttributeExample object = new IgnoredAttributeExample("halab", "apollo");
        Document document = mapper.toDocument(object);
        assertEquals(object.getNormal(), document.getString("normal"));
        assertNull(document.getString("ignored"));
    }

    @Test
    @DisplayName("Test deserialize with ignored attributes")
    public void testDeserializeIgnoredAttributes() {
        Document document = new Document("normal", "halab").append("ignored", "apollo");
        IgnoredAttributeExample object = mapper.toObject(document, IgnoredAttributeExample.class);

        assertEquals(document.getString("normal"), object.getNormal());
        assertNull(object.getIgnored());
    }
}
