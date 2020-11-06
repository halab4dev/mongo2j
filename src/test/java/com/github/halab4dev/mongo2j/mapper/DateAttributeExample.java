package com.github.halab4dev.mongo2j.mapper;

import java.util.Date;

/**
 * author halab
 */
public class DateAttributeExample {

    private String name;
    private Date birthday;

    public DateAttributeExample() {
    }

    public DateAttributeExample(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
