package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonIgnore;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 * author halab
 */
public class IgnoredAttributeExample {

    private String normal;

    @BsonIgnore
    private String ignored;

    public IgnoredAttributeExample() {
    }

    public IgnoredAttributeExample(String normal, String ignored) {
        this.normal = normal;
        this.ignored = ignored;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getIgnored() {
        return ignored;
    }

    public void setIgnored(String ignored) {
        this.ignored = ignored;
    }
}
