package com.github.halab4dev.mongo2j.mapper;

import java.util.Map;

/*
 *
 * @author halab
 */
public class MapAttributeExample {

    private Map<String, Integer> simpleTypeMap;
    private Map<String, WrappedAttributesExample> complexMap;

    public MapAttributeExample() {
    }

    public MapAttributeExample(Map<String, Integer> simpleTypeMap, Map<String, WrappedAttributesExample> complexMap) {
        this.simpleTypeMap = simpleTypeMap;
        this.complexMap = complexMap;
    }

    public Map<String, Integer> getSimpleTypeMap() {
        return simpleTypeMap;
    }

    public void setSimpleTypeMap(Map<String, Integer> simpleTypeMap) {
        this.simpleTypeMap = simpleTypeMap;
    }

    public Map<String, WrappedAttributesExample> getComplexMap() {
        return complexMap;
    }

    public void setComplexMap(Map<String, WrappedAttributesExample> complexMap) {
        this.complexMap = complexMap;
    }

    @Override
    public String toString() {
        return "MapAttributeExample{" +
                "simpleTypeMap=" + simpleTypeMap +
                ", complexMap=" + complexMap +
                '}';
    }
}
