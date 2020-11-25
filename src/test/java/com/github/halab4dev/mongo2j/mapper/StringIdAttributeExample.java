package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import com.github.halab4dev.mongo2j.annotation.BsonProperty;
import org.bson.types.ObjectId;

/*
 *
 * @author halab
 */
public class StringIdAttributeExample {

    @BsonId
    private String id;

    private String name;

    @BsonProperty(value = "string_id", isObjectId = true)
    private String stringId;

    public StringIdAttributeExample() {
    }

    public StringIdAttributeExample(String objectId, String name, String stringId) {
        this.id = objectId;
        this.name = name;
        this.stringId = stringId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "StringIdAttributeExample{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
