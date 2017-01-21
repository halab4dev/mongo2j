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
public class Developer extends Person {

    private int age;
    private Address address;
    @DocumentField(name = "skills")
    private List<String> languages;

    public Developer() {
        languages = new ArrayList<>();
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
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", languages=" + languages +
                '}';
    }
}
