package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 *
 * @author halab
 */
public class DefaultMapperCollectionAttributeTests {

    Mapper mapper = new DefaultMapper();

    @Test
    @DisplayName("Test serialize with empty collection attributes")
    public void testSerializeEmptyCollectionAttributes() {
        Set<CollectionAttributesExample.Element> elements = new HashSet<>();
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setSet(elements);

        Document document = mapper.toDocument(object);
        System.out.println(document);

        List<Document> documentElements = document.getList("set", Document.class);
        assertEquals(0, documentElements.size());
    }

    @Test
    @DisplayName("Test serialize with set attributes")
    public void testSerializeSetAttributes() {
        Set<CollectionAttributesExample.Element> elements = new HashSet<>(Arrays.asList(
                new CollectionAttributesExample.Element(1, "One"),
                new CollectionAttributesExample.Element(2, "Two")
        ));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setSet(elements);

        Document document = mapper.toDocument(object);

        List<Document> documentElements = document.getList("set", Document.class);
        List<CollectionAttributesExample.Element> elementsList = new ArrayList<>(elements);

        for(int i = 0; i < elementsList.size(); i++) {
            assertEquals(elementsList.get(i).getId(), documentElements.get(i).getInteger("id"));
            assertEquals(elementsList.get(i).getName(), documentElements.get(i).getString("name"));
        }
    }

    @Test
    @DisplayName("Test serialize with hash set attributes")
    public void testSerializeHashSetAttributes() {
        HashSet<Long> elements = new HashSet<>(Arrays.asList(1L, 2L, 3L));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setHashSet(elements);

        Document document = mapper.toDocument(object);

        List<Long> documentElements = document.getList("hashSet", Long.class);
        List<Long> elementsList = new ArrayList<>(elements);

        for(int i = 0; i < elementsList.size(); i++) {
            assertEquals(elementsList.get(i), documentElements.get(i));
            assertEquals(elementsList.get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with linked hash set set attributes")
    public void testSerializeLinkedHashSetAttributes() {
        LinkedHashSet<String> elements = new LinkedHashSet<>(Arrays.asList("1L", "2L", "3L"));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setLinkedHashSet(elements);

        Document document = mapper.toDocument(object);

        List<String> documentElements = document.getList("linkedHashSet", String.class);
        List<String> elementsList = new ArrayList<>(elements);

        for(int i = 0; i < elementsList.size(); i++) {
            assertEquals(elementsList.get(i), documentElements.get(i));
            assertEquals(elementsList.get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with tree set set attributes")
    public void testSerializeTreeSetAttributes() {
        TreeSet<Integer> elements = new TreeSet<>(Arrays.asList(1, 2, 3));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setTreeSet(elements);

        Document document = mapper.toDocument(object);

        List<Integer> documentElements = document.getList("treeSet", Integer.class);
        List<Integer> elementsList = new ArrayList<>(elements);

        for(int i = 0; i < elementsList.size(); i++) {
            assertEquals(elementsList.get(i), documentElements.get(i));
            assertEquals(elementsList.get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with list attributes")
    public void testSerializeListAttributes() {
        List<CollectionAttributesExample.Element> elements = new ArrayList<>(Arrays.asList(
                new CollectionAttributesExample.Element(1, "One"),
                new CollectionAttributesExample.Element(2, "Two")
        ));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setList(elements);
        Document document = mapper.toDocument(object);

        List<Document> documentElements = document.getList("list", Document.class);
        for(int i = 0; i < object.getList().size(); i++) {
            assertEquals(object.getList().get(i).getId(), documentElements.get(i).getInteger("id"));
            assertEquals(object.getList().get(i).getName(), documentElements.get(i).getString("name"));
        }
    }

    @Test
    @DisplayName("Test serialize with array list attributes")
    public void testSerializeArrayListAttributes() {
        ArrayList<Double> elements = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setArrayList(elements);
        Document document = mapper.toDocument(object);

        List<Double> documentElements = document.getList("arrayList", Double.class);
        for(int i = 0; i < object.getArrayList().size(); i++) {
            assertEquals(object.getArrayList().get(i), documentElements.get(i));
            assertEquals(object.getArrayList().get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with linked list attributes")
    public void testSerializeLinkedListAttributes() {
        LinkedList<Short> elements = new LinkedList<>(Arrays.asList((short)1, (short)2, (short)3));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setLinkedList(elements);
        Document document = mapper.toDocument(object);

        List<Short> documentElements = document.getList("linkedList", Short.class);
        for(int i = 0; i < object.getLinkedList().size(); i++) {
            assertEquals(object.getLinkedList().get(i), documentElements.get(i));
            assertEquals(object.getLinkedList().get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with vector attributes")
    public void testSerializeVectorAttributes() {
        Vector<Float> elements = new Vector<>(Arrays.asList((float)1.0, (float)2.0, (float)3.0));
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setVector(elements);
        Document document = mapper.toDocument(object);

        List<Float> documentElements = document.getList("vector", Float.class);
        for(int i = 0; i < object.getVector().size(); i++) {
            assertEquals(object.getVector().get(i), documentElements.get(i));
            assertEquals(object.getVector().get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test serialize with stack attributes")
    public void testSerializeStackAttributes() {
        Stack<Boolean> elements = new Stack<>();
        elements.push(true);
        elements.push(false);
        elements.push(true);
        CollectionAttributesExample object = new CollectionAttributesExample();
        object.setStack(elements);
        Document document = mapper.toDocument(object);

        List<Boolean> documentElements = document.getList("stack", Boolean.class);
        for(int i = 0; i < object.getStack().size(); i++) {
            assertEquals(object.getStack().get(i), documentElements.get(i));
            assertEquals(object.getStack().get(i), documentElements.get(i));
        }
    }

    @Test
    @DisplayName("Test deserialize with set attributes")
    public void testDeserializeSetAttributes() {
        Set<Document> documentElements = new HashSet<>(Arrays.asList(
                new Document("id", 1).append("name", "One"),
                new Document("id", 2).append("name", "Two")
        ));

        Document document = new Document("set", documentElements);

        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        List<Document> documentList = new ArrayList<>(documentElements);
        List<CollectionAttributesExample.Element> elementList = new ArrayList<>(object.getSet());
        assertEquals(documentElements.size(), elementList.size());
        for (Document value : documentList) {
            int id = value.getInteger("id");
            boolean checkResult = false;
            for (CollectionAttributesExample.Element element : elementList) {
                if (id == element.getId()) {
                    assertEquals(value.getString("name"), element.getName());
                    checkResult = true;
                }
            }
            if (!checkResult) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("Test deserialize with hash set attributes")
    public void testDeserializeHashSetAttributes() {
        HashSet<Long> documentElements = new HashSet<>(Arrays.asList(1L, 2L, 3L));

        Document document = new Document("hashSet", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getHashSet().size());
        for (Long value : documentElements) {
            assertTrue(object.getHashSet().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with tree set attributes")
    public void testDeserializeTreeSetAttributes() {
        TreeSet<Integer> documentElements = new TreeSet<>(Arrays.asList(1, 2, 3));

        Document document = new Document("treeSet", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getTreeSet().size());
        for (Integer value : documentElements) {
            assertTrue(object.getTreeSet().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with linked hash set attributes")
    public void testDeserializeLinkedHashSetAttributes() {
        LinkedHashSet<String> documentElements = new LinkedHashSet<>(Arrays.asList("1L", "2L", "3L"));

        Document document = new Document("linkedHashSet", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getLinkedHashSet().size());
        for (String value : documentElements) {
            assertTrue(object.getLinkedHashSet().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with list attributes")
    public void testDeserializeListAttributes() {
        List<Document> documentElements = new ArrayList<>(Arrays.asList(
                new Document("id", 1).append("name", "One"),
                new Document("id", 2).append("name", "Two")
        ));

        Document document = new Document("list", documentElements);

        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        List<Document> documentList = new ArrayList<>(documentElements);
        List<CollectionAttributesExample.Element> elementList = new ArrayList<>(object.getList());
        assertEquals(documentElements.size(), elementList.size());
        for (Document value : documentList) {
            int id = value.getInteger("id");
            boolean checkResult = false;
            for (CollectionAttributesExample.Element element : elementList) {
                if (id == element.getId()) {
                    assertEquals(value.getString("name"), element.getName());
                    checkResult = true;
                }
            }
            if (!checkResult) {
                fail();
            }
        }
    }

    @Test
    @DisplayName("Test deserialize with array list attributes")
    public void testDeserializeArrayListAttributes() {
        ArrayList<Double> documentElements = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0));

        Document document = new Document("arrayList", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getArrayList().size());
        for (Double value : documentElements) {
            assertTrue(object.getArrayList().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with linked list attributes")
    public void testDeserializeLinkedListAttributes() {
        LinkedList<Short> documentElements = new LinkedList<>(Arrays.asList((short)1, (short)2, (short)3));

        Document document = new Document("linkedList", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getLinkedList().size());
        for (Short value : documentElements) {
            assertTrue(object.getLinkedList().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with vector attributes")
    public void testDeserializeVectorAttributes() {
        Vector<Float> documentElements = new Vector<>(Arrays.asList((float)1, (float)2, (float)3));

        Document document = new Document("vector", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getVector().size());
        for (Float value : documentElements) {
            assertTrue(object.getVector().contains(value));
        }
    }

    @Test
    @DisplayName("Test deserialize with stack attributes")
    public void testDeserializeStackAttributes() {
        Stack<Boolean> documentElements = new Stack<>();
        documentElements.push(true);
        documentElements.push(false);
        documentElements.push(true);

        Document document = new Document("stack", documentElements);
        CollectionAttributesExample object = mapper.toObject(document, CollectionAttributesExample.class);

        assertEquals(documentElements.size(), object.getStack().size());

        for(int i = 0; i < documentElements.size(); i++) {
            assertEquals(documentElements.get(i), object.getStack().get(i));
        }
    }


}
