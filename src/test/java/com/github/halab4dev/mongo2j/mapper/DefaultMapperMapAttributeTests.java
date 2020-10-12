package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * @author halab
 */
public class DefaultMapperMapAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with map attributes")
    public void testSerializeMapAttributes() {
        Map<String, Integer> simpleTypeMap = new HashMap<>();
        simpleTypeMap.put("key1", 1);
        simpleTypeMap.put("key2", 2);

        Map<String, WrappedAttributesExample> complexMap = new HashMap<>();
        complexMap.put("keyA", new WrappedAttributesExample('A'));
        complexMap.put("keyB", new WrappedAttributesExample('B'));

        MapAttributeExample object = new MapAttributeExample(simpleTypeMap, complexMap);

        Document document = mapper.toDocument(object);
        System.out.println(document.toJson());

        assertEquals(object.getSimpleTypeMap().get("key1"),
                ((Document)document.get("simpleTypeMap")).get("key1"));
        assertEquals(object.getSimpleTypeMap().get("key2"),
                ((Document)document.get("simpleTypeMap")).get("key2"));

        assertEquals(object.getComplexMap().get("keyA").getCharAttribute(),
                ((Document)((Document)document.get("complexMap")).get("keyA")).get("charAttribute"));
        assertEquals(object.getComplexMap().get("keyB").getCharAttribute(),
                ((Document)((Document)document.get("complexMap")).get("keyB")).get("charAttribute"));
    }

    @Test
    @DisplayName("Test deserialize with map attributes")
    public void testDeserializeMapAttributes() {
        Document document = new Document("simpleTypeMap",
                new Document("key1", 1).append("key2", 2))
                .append("complexMap", new Document("keyA", new Document("charAttribute", 'A'))
                        .append("keyB", new Document("charAttribute",'B')));
        System.out.println(document.toJson());
        MapAttributeExample object = mapper.toObject(document, MapAttributeExample.class);
        System.out.println(object);

        assertEquals(((Document)document.get("simpleTypeMap")).get("key1"),
                object.getSimpleTypeMap().get("key1"));
        assertEquals(((Document)document.get("simpleTypeMap")).get("key2"),
                object.getSimpleTypeMap().get("key2"));

        assertEquals(((Document)((Document)document.get("complexMap")).get("keyA")).get("charAttribute"),
                object.getComplexMap().get("keyA").getCharAttribute());
        assertEquals(((Document)((Document)document.get("complexMap")).get("keyB")).get("charAttribute"),
                object.getComplexMap().get("keyB").getCharAttribute());
    }
}
