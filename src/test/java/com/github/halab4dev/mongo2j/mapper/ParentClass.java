package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonProperty;

import java.util.List;

/*
 *
 * @author halab
 */
public class ParentClass extends GrandParentClass {

    private int age;

    @BsonProperty("other_names")
    private List<String> nicknames;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }
}
