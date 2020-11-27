package com.github.halab4dev.mongo2j.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * author halab
 */
public class ClassUtilsTests {

    @Test
    @DisplayName("Test validate simple field by field")
    public void testValidateSimpleFieldByField() throws NoSuchFieldException {
        ColorfulObject object = new ColorfulObject();
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallByte")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallShort")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallInt")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallLong")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallFloat")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallDouble")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallChar")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("smallBoolean")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigByte")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigShort")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigInt")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigLong")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigFloat")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigDouble")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigChar")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("bigBoolean")));
        assertTrue(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("string")));
        assertFalse(ClassUtils.isSimpleValue(object.getClass().getDeclaredField("colorful")));
    }

    @Test
    @DisplayName("Test validate simple field by object")
    public void testValidateSimpleFieldByObject() throws NoSuchFieldException {
        assertTrue(ClassUtils.isSimpleValue(new Short((short) 1)));
        assertTrue(ClassUtils.isSimpleValue(new Integer(1)));
        assertTrue(ClassUtils.isSimpleValue(new Long(1)));
        assertTrue(ClassUtils.isSimpleValue(new Float(1.0)));
        assertTrue(ClassUtils.isSimpleValue(new Double(1.0)));
        assertTrue(ClassUtils.isSimpleValue(new Character('a')));
        assertTrue(ClassUtils.isSimpleValue(new Boolean(true)));
        assertTrue(ClassUtils.isSimpleValue("halab"));
        assertFalse(ClassUtils.isSimpleValue(new ColorfulField()));
    }

    @Test
    @DisplayName("Test validate date field")
    public void testValidateDateField() throws NoSuchFieldException {
        ColorfulObject object = new ColorfulObject();
        assertTrue(ClassUtils.isDate(object.getClass().getDeclaredField("date")));
        assertFalse(ClassUtils.isDate(object.getClass().getDeclaredField("string")));
    }

    @Test
    @DisplayName("Test validate collection field")
    public void testValidateCollectionField() throws NoSuchFieldException {
        ColorfulObject object = new ColorfulObject();
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("list")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("arrayList")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("linkedList")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("stack")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("vector")));

        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("set")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("hashSet")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("linkedHashSet")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("sortedSet")));
        assertTrue(ClassUtils.isCollection(object.getClass().getDeclaredField("treeSet")));

        assertFalse(ClassUtils.isCollection(object.getClass().getDeclaredField("string")));
    }

    @Test
    @DisplayName("Test validate map field")
    public void testValidateMapField() throws NoSuchFieldException {
        ColorfulObject object = new ColorfulObject();
        assertTrue(ClassUtils.isMap(object.getClass().getDeclaredField("map")));
        assertTrue(ClassUtils.isMap(object.getClass().getDeclaredField("hashMap")));

        assertFalse(ClassUtils.isMap(object.getClass().getDeclaredField("string")));
    }

    @Test
    @DisplayName("Test validate wrapped class field by object")
    public void testValidateWrappedClassFieldByObject() throws NoSuchFieldException {
        assertTrue(ClassUtils.isStringOrWrappedClass(new Short((short) 1)));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Integer(1)));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Long(1)));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Float(1.0)));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Double(1.0)));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Character('a')));
        assertTrue(ClassUtils.isStringOrWrappedClass(new Boolean(true)));
        assertTrue(ClassUtils.isStringOrWrappedClass("halab"));
        assertFalse(ClassUtils.isStringOrWrappedClass(new ColorfulField()));
    }

    @Test
    @DisplayName("Test validate wrapped class field by class")
    public void testValidateWrappedClassFieldByClass() throws NoSuchFieldException {
        assertTrue(ClassUtils.isStringOrWrappedClass(Short.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Integer.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Long.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Float.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Double.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Character.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(Boolean.class));
        assertTrue(ClassUtils.isStringOrWrappedClass(String.class));
        assertFalse(ClassUtils.isStringOrWrappedClass(ColorfulField.class));
    }

    @Test
    @DisplayName("Test get super class field")
    public void testGetSuperClassFields() {
        List<Field> fields = ClassUtils.getSuperClassField(ColorfulObject.class);
        List<String> fieldNames = fields.stream().map(Field::getName).collect(Collectors.toList());
        List<String> expectedFieldNames = Arrays.asList("grandParent", "parent");
        fieldNames.forEach(fieldName -> assertTrue(expectedFieldNames.contains(fieldName)));
    }

    @Test
    @DisplayName("Test get class field")
    public void testGetClassFields() {
        List<Field> fields = ClassUtils.getClassField(ColorfulObject.class);
        List<String> fieldNames = fields.stream().map(Field::getName).collect(Collectors.toList());
        List<String> expectedFieldNames = Arrays.asList("smallByte", "smallShort", "smallInt", "smallLong",
                "smallFloat", "smallDouble", "smallChar", "smallBoolean",
                "bigByte", "bigShort", "bigInt", "bigLong",
                "bigFloat", "bigDouble", "bigChar", "bigBoolean",
                "string", "date", "map", "hashMap", "colorful",
                "list", "arrayList", "linkedList", "stack", "vector",
                "set", "hashSet", "linkedHashSet", "sortedSet", "treeSet");
        fieldNames.forEach(fieldName -> assertTrue(expectedFieldNames.contains(fieldName)));
    }

    @Test
    @DisplayName("Test validate static final field")
    public void testValidateStaticFinalField() throws NoSuchFieldException {
        ColorfulObject object = new ColorfulObject();
        assertTrue(ClassUtils.isStaticFinal(object.getClass().getField("CONSTANT")));
    }
}
