package com.github.halab4dev.mongo2j.mapper;

import java.util.*;

/*
 *
 * @author halab
 */
public class CollectionAttributesExample {

    private Set<Element> set;
    private TreeSet<Integer> treeSet;
    private LinkedHashSet<String> linkedHashSet;
    private HashSet<Long> hashSet;
    private List<Element> list;
    private ArrayList<Double> arrayList;
    private LinkedList<Short> linkedList;
    private Vector<Float> vector;
    private Stack<Boolean> stack;

    public CollectionAttributesExample() {
    }

    public Set<Element> getSet() {
        return set;
    }

    public void setSet(Set<Element> set) {
        this.set = set;
    }

    public TreeSet<Integer> getTreeSet() {
        return treeSet;
    }

    public void setTreeSet(TreeSet<Integer> treeSet) {
        this.treeSet = treeSet;
    }

    public LinkedHashSet<String> getLinkedHashSet() {
        return linkedHashSet;
    }

    public void setLinkedHashSet(LinkedHashSet<String> linkedHashSet) {
        this.linkedHashSet = linkedHashSet;
    }

    public HashSet<Long> getHashSet() {
        return hashSet;
    }

    public void setHashSet(HashSet<Long> hashSet) {
        this.hashSet = hashSet;
    }

    public List<Element> getList() {
        return list;
    }

    public void setList(List<Element> list) {
        this.list = list;
    }

    public ArrayList<Double> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Double> arrayList) {
        this.arrayList = arrayList;
    }

    public LinkedList<Short> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<Short> linkedList) {
        this.linkedList = linkedList;
    }

    public Vector<Float> getVector() {
        return vector;
    }

    public void setVector(Vector<Float> vector) {
        this.vector = vector;
    }

    public Stack<Boolean> getStack() {
        return stack;
    }

    public void setStack(Stack<Boolean> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "CollectionAttributesExample{" +
                "set=" + set +
                ", treeSet=" + treeSet +
                ", linkedHashSet=" + linkedHashSet +
                ", hashSet=" + hashSet +
                ", list=" + list +
                ", arrayList=" + arrayList +
                ", linkedList=" + linkedList +
                ", vector=" + vector +
                ", stack=" + stack +
                '}';
    }

    public static class Element {

        private int id;
        private String name;

        public Element() {
        }

        public Element(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Element)) return false;
            Element that = (Element) o;
            return id == that.id &&
                    name.equals(that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
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
            return "Element{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
