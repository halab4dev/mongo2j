package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import com.github.halab4dev.mongo2j.annotation.BsonProperty;
import org.bson.types.ObjectId;

/*
 *
 * @author halab
 */
public class ObjectIdAttributeExample {

    @BsonId
    private ObjectId objectId;

    private String name;

    @BsonProperty(value = "other_object_id", isObjectId = true)
    private ObjectId otherObjectId;

    public ObjectIdAttributeExample() {
    }

    public ObjectIdAttributeExample(ObjectId objectId, String name, ObjectId otherObjectId) {
        this.objectId = objectId;
        this.name = name;
        this.otherObjectId = otherObjectId;
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

    public ObjectId getOtherObjectId() {
        return otherObjectId;
    }

    public void setOtherObjectId(ObjectId otherObjectId) {
        this.otherObjectId = otherObjectId;
    }

    @Override
    public String toString() {
        return "ObjectIdAttributeExample{" +
                "objectId=" + objectId +
                ", name='" + name + '\'' +
                ", otherObjectId=" + otherObjectId +
                '}';
    }
}
