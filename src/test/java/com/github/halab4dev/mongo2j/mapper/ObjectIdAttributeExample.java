package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import org.bson.types.ObjectId;

/*
 *
 * @author halab
 */
public class ObjectIdAttributeExample {

    @BsonId
    private ObjectId objectId;

    private String name;

    public ObjectIdAttributeExample() {
    }

    public ObjectIdAttributeExample(ObjectId objectId, String name) {
        this.objectId = objectId;
        this.name = name;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ObjectIdAttributeExample{" +
                "objectId=" + objectId +
                ", name='" + name + '\'' +
                '}';
    }
}
