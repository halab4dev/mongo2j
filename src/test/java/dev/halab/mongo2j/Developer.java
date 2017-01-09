package dev.halab.mongo2j;

import dev.halab.mongo2j.annotation.DocumentField;
import dev.halab.mongo2j.annotation.DocumentId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Apollo
 */
public class Developer {

    @DocumentId
    private String id;
    private String name;
    private int age;
    private Address address;
    @DocumentField(name = "skills")
    private List<String> languages;

    public Developer() {
        languages = new ArrayList<>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", languages=" + languages +
                '}';
    }
}
