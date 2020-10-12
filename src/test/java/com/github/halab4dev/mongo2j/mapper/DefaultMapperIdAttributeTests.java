package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * @author halab
 */
public class DefaultMapperIdAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with object id attributes")
    public void testSerializeObjectIdAttributes() {
        ObjectIdAttributeExample object = new ObjectIdAttributeExample(new ObjectId(), "halab");
        Document document = mapper.toDocument(object);
        assertEquals(object.getObjectId(), document.getObjectId("_id"));
        assertEquals(object.getName(), document.getString("name"));
    }

    @Test
    @DisplayName("Test deserialize with object id attributes")
    public void testDeserializeObjectIdAttributes() {
        Document document = new Document("_id", new ObjectId()).append("name", "halab");
        ObjectIdAttributeExample object = mapper.toObject(document, ObjectIdAttributeExample.class);

        assertEquals(document.getObjectId("_id"), object.getObjectId());
        assertEquals(document.getString("name"), object.getName());
    }

    @Test
    @DisplayName("Test serialize with string id attributes")
    public void testSerializeStringIdAttributes() {
        StringIdAttributeExample object = new StringIdAttributeExample(new ObjectId().toString(), "halab");
        Document document = mapper.toDocument(object);
        assertEquals(object.getId(), document.getObjectId("_id").toString());
        assertEquals(object.getName(), document.getString("name"));
    }

    @Test
    @DisplayName("Test deserialize with string id attributes")
    public void testDeserializeStringIdAttributes() {
        Document document = new Document("_id", new ObjectId()).append("name", "halab");
        StringIdAttributeExample object = mapper.toObject(document, StringIdAttributeExample.class);

        assertEquals(document.getObjectId("_id").toString(), object.getId());
        assertEquals(document.getString("name"), object.getName());
    }
}
