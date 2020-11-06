package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 *
 * @author halab
 */
public class DefaultMapperSimpleAttributesTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with null object")
    public void testSerializeNullObject() {
        Document document = mapper.toDocument(null);
        assertNull(document);
    }

    @Test
    @DisplayName("Test deserialize with null document")
    public void testDeserializeNullDocument() {
        Object object = mapper.toObject(null, Object.class);
        assertNull(object);
    }

    @Test
    @DisplayName("Test serialize with primitive attributes")
    public void testSerializePrimitiveAttributes() {
        PrimitiveAttributesExample object = new PrimitiveAttributesExample();
        object.setBooleanAttribute(true);
        object.setByteAttribute((byte) 1);
        object.setShortAttribute((short) 5);
        object.setIntegerAttribute(10);
        object.setLongAttribute(20L);
        object.setFloatAttribute(50F);
        object.setDoubleAttribute(100D);
        object.setCharAttribute('a');

        Document document = mapper.toDocument(object);

        assertEquals(object.isBooleanAttribute(), document.getBoolean("booleanAttribute"));
        assertEquals(object.getByteAttribute(), (byte) document.get("byteAttribute"));
        assertEquals(object.getShortAttribute(), (short) document.get("shortAttribute"));
        assertEquals(object.getIntegerAttribute(), document.getInteger("integerAttribute"));
        assertEquals(object.getLongAttribute(), document.getLong("longAttribute"));
        assertEquals(object.getFloatAttribute(), (float) document.get("floatAttribute"));
        assertEquals(object.getDoubleAttribute(), document.getDouble("doubleAttribute"));
        assertEquals(object.getCharAttribute(), (char) document.get("charAttribute"));
    }


    @Test
    @DisplayName("Test deserialize with primitive attributes")
    public void testDeserializePrimitiveAttributes() {
        Document document = new Document("booleanAttribute", true)
                .append("byteAttribute", (byte) 1)
                .append("shortAttribute", (short) 5)
                .append("integerAttribute", 10)
                .append("longAttribute", 20L)
                .append("floatAttribute", 50F)
                .append("doubleAttribute", 100D)
                .append("charAttribute", 'a');

        PrimitiveAttributesExample object = mapper.toObject(document, PrimitiveAttributesExample.class);

        assertTrue(object.isBooleanAttribute());
        assertEquals((byte) 1, object.getByteAttribute());
        assertEquals((short) 5, object.getShortAttribute());
        assertEquals(10, object.getIntegerAttribute());
        assertEquals(20L, object.getLongAttribute());
        assertEquals(50F, object.getFloatAttribute());
        assertEquals(100D, object.getDoubleAttribute());
        assertEquals('a', object.getCharAttribute());
    }

    @Test
    @DisplayName("Test serialize with wrapped attributes")
    public void testSerializeWrappedAttributes() {
        PrimitiveAttributesExample object = new PrimitiveAttributesExample();
        object.setBooleanAttribute(true);
        object.setByteAttribute((byte) 1);
        object.setShortAttribute((short) 5);
        object.setIntegerAttribute(10);
        object.setLongAttribute(20L);
        object.setFloatAttribute(50F);
        object.setDoubleAttribute(100D);
        object.setCharAttribute('a');

        Document document = mapper.toDocument(object);

        assertEquals(object.isBooleanAttribute(), document.getBoolean("booleanAttribute"));
        assertEquals(object.getByteAttribute(), (byte) document.get("byteAttribute"));
        assertEquals(object.getShortAttribute(), (short) document.get("shortAttribute"));
        assertEquals(object.getIntegerAttribute(), document.getInteger("integerAttribute"));
        assertEquals(object.getLongAttribute(), document.getLong("longAttribute"));
        assertEquals(object.getFloatAttribute(), (float) document.get("floatAttribute"));
        assertEquals(object.getDoubleAttribute(), document.getDouble("doubleAttribute"));
        assertEquals(object.getCharAttribute(), (char) document.get("charAttribute"));
    }


    @Test
    @DisplayName("Test deserialize with wrapped attributes")
    public void testDeserializeWrappedAttributes() {
        Document document = new Document("booleanAttribute", true)
                .append("byteAttribute", (byte) 1)
                .append("shortAttribute", (short) 5)
                .append("integerAttribute", 10)
                .append("longAttribute", 20L)
                .append("floatAttribute", 50F)
                .append("doubleAttribute", 100D)
                .append("charAttribute", 'a');

        PrimitiveAttributesExample object = mapper.toObject(document, PrimitiveAttributesExample.class);

        assertTrue(object.isBooleanAttribute());
        assertEquals((byte) 1, object.getByteAttribute());
        assertEquals((short) 5, object.getShortAttribute());
        assertEquals(10, object.getIntegerAttribute());
        assertEquals(20L, object.getLongAttribute());
        assertEquals(50F, object.getFloatAttribute());
        assertEquals(100D, object.getDoubleAttribute());
        assertEquals('a', object.getCharAttribute());
    }
}
