package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * @author halab
 */
public class DefaultMapperComplexAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with complex object")
    public void testSerializeComplexObject() {
        ComplexAttributeExample.Address address =
                new ComplexAttributeExample.Address("Quang Trung", "Hanoi");
        ComplexAttributeExample object = new ComplexAttributeExample("halab", address);
        Document document = mapper.toDocument(object);

        assertEquals(object.getName(), document.getString("name"));
        assertEquals(object.getAddress().getStreet(),
                document.get("address", Document.class).getString("street"));
        assertEquals(object.getAddress().getCity(),
                document.get("address", Document.class).getString("city"));
    }


    @Test
    @DisplayName("Test deserialize with complex object")
    public void testDeserializeComplexObject() {
        Document document = new Document("name", "halab")
                .append("address", new Document("street", "Quang Trung").append("city", "Hanoi"));
        ComplexAttributeExample object = mapper.toObject(document, ComplexAttributeExample.class);

        assertEquals(document.getString("name"), object.getName());
        assertEquals(document.get("address", Document.class).getString("street"),
                object.getAddress().getStreet());
        assertEquals(document.get("address", Document.class).getString("city"),
                object.getAddress().getCity());
    }

}
