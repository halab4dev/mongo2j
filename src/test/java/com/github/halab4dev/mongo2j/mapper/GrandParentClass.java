package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import com.github.halab4dev.mongo2j.annotation.BsonProperty;

/*
 *
 * @author halab
 */
public class GrandParentClass {

    @BsonId
    private String id;
    private String name;

    public GrandParentClass() {
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
}
