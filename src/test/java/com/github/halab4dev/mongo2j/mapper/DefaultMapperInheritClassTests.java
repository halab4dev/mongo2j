package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 *
 * @author halab
 */
public class DefaultMapperInheritClassTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with inherit class")
    public void testSerializeInheritClass() {
        ChildClass object = new ChildClass();
        object.setId(new ObjectId().toString());
        object.setName("halab");
        object.setNicknames(Arrays.asList("superman", "god"));
        object.setAge(20);
        object.setAccountId("abcdef1234567890");
        object.setBalance(500.5);

        Document document = mapper.toDocument(object);

        assertEquals(object.getId(), document.getObjectId("_id").toString());
        assertEquals(object.getName(), document.getString("name"));
        assertEquals(object.getAge(), document.getInteger("age"));
        assertEquals(object.getNicknames(), document.get("other_names"));
        assertEquals(object.getAccountId(), document.getString("account_id"));
        assertEquals(object.getBalance(), document.getDouble("balance"));
    }



    @Test
    @DisplayName("Test deserialize with inherit class")
    public void testDeserializeInheritClass() {
        Document document = new Document("_id", new ObjectId())
                .append("name", "halab")
                .append("age", 20)
                .append("other_names", Arrays.asList("superman", "god"))
                .append("account_id", "abcdef1234567890")
                .append("balance", 500.5);

        ChildClass object = mapper.toObject(document, ChildClass.class);

        assertEquals(document.getObjectId("_id").toString(), object.getId());
        assertEquals(document.getString("name"), object.getName());
        assertEquals(document.getInteger("age"), object.getAge());
        assertEquals(document.get("other_names"), object.getNicknames());
        assertEquals(document.getString("account_id"), object.getAccountId());
        assertEquals(document.getDouble("balance"), object.getBalance());
    }


}
