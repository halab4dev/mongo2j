package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;

/*
 *
 * @author halab
 */
public class StringIdAttributeExample {

    @BsonId
    private String id;

    private String name;

    public StringIdAttributeExample() {
    }

    public StringIdAttributeExample(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "StringIdAttributeExample{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
