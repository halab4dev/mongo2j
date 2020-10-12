package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import com.github.halab4dev.mongo2j.util.BsonUtils;
import com.github.halab4dev.mongo2j.util.ClassUtils;
import com.github.halab4dev.mongo2j.util.Validator;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/*
 *
 * @author halab
 */
public class BsonDeserializer {

    private static final String ID = "_id";

    public <T> T toObject(Document document, Class<T> type) {
        if (Validator.isNull(document)) {
            return null;
        }
        T t;
        try {
            t = type.newInstance();
            List<Field> fields = ClassUtils.getSuperClassField(type);
            fields.addAll(Arrays.asList(type.getDeclaredFields()));
            for (Field field : fields) {
                setObjectField(t, field, document);
            }

        } catch (InstantiationException e) {
            throw new RuntimeException("Class does not have no argument constructor", e);
        } catch (IllegalAccessException | NoSuchMethodException
                | InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return t;
    }


    /**
     * Set object field
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    private void setObjectField(Object object, Field field, Document document) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        field.setAccessible(true);

        if (field.isAnnotationPresent(BsonId.class)) {
            setObjectIdField(object, field, document);

        } else if (ClassUtils.isSimpleValue(field)) {
            setSimpleValueField(object, field, document);

        } else if (ClassUtils.isCollection(field)) {
            setCollectionField(object, field, document);

        } else if (ClassUtils.isMap(field)) {
            setMapField(object, field, document);
        } else {
            setComplexValueField(object, field, document);
        }
    }


    /**
     * Set id field
     *
     * @param object   java object
     * @param field    class attribute marked as id
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    private void setObjectIdField(Object object, Field field, Document document) throws IllegalAccessException {
        ObjectId objectId = document.getObjectId(ID);
        if (Validator.isNull(objectId)) {
            return;
        }

        if (field.getType().isAssignableFrom(ObjectId.class)) {
            field.set(object, objectId);
        } else if (field.getType().isAssignableFrom(String.class)) {
            field.set(object, objectId.toString());
        } else {
            throw new IllegalArgumentException("DocumentId field must be Object Id or String");
        }
    }

    private static final String STRING = "String";
    private static final String CHARACTER = "Character";
    private static final String PRIMITIVE_CHARACTER = "char";
    private static final String BOOLEAN = "Boolean";
    private static final String PRIMITIVE_BOOLEAN = "boolean";
    private static final String BYTE = "Byte";
    private static final String PRIMITIVE_BYTE = "byte";
    private static final String SHORT = "Short";
    private static final String PRIMITIVE_SHORT = "short";
    private static final String INTEGER = "Integer";
    private static final String PRIMITIVE_INTEGER = "int";
    private static final String LONG = "Long";
    private static final String PRIMITIVE_LONG = "long";
    private static final String FLOAT = "Float";
    private static final String PRIMITIVE_FLOAT = "float";
    private static final String DOUBLE = "Double";
    private static final String PRIMITIVE_DOUBLE = "double";


    /**
     * Set value for primitive or wrapped class attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    private void setSimpleValueField(Object object, Field field, Document document) throws IllegalAccessException {
        String fieldName = BsonUtils.getDocumentFieldName(field);
        if (Validator.isNull(document.get(fieldName))) {
            return;
        }

        Class<?> fieldClass = field.getType();
        switch (fieldClass.getSimpleName()) {
            case STRING:
                field.set(object, document.getString(fieldName));
                break;
            case PRIMITIVE_BOOLEAN:
            case BOOLEAN:
                field.set(object, document.getBoolean(fieldName));
                break;
            case PRIMITIVE_INTEGER:
            case INTEGER:
                field.set(object, document.getInteger(fieldName));
                break;
            case PRIMITIVE_LONG:
            case LONG:
                field.set(object, document.getLong(fieldName));
                break;
            case PRIMITIVE_CHARACTER:
            case CHARACTER:
            case PRIMITIVE_BYTE:
            case BYTE:
            case PRIMITIVE_SHORT:
            case SHORT:
            case PRIMITIVE_FLOAT:
            case FLOAT:
                field.set(object, document.get(fieldName));
                break;
            case PRIMITIVE_DOUBLE:
            case DOUBLE:
                field.set(object, document.getDouble(fieldName));
                break;
        }
    }


    /**
     * Set value for collection attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    @SuppressWarnings("unchecked")
    private void setCollectionField(Object object, Field field, Document document) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String fieldName = BsonUtils.getDocumentFieldName(field);
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        Class<?> elementType = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        if (Set.class.isAssignableFrom(field.getType())) {
            Set<Document> dbSet = document.get(fieldName, new HashSet<>());
            Set collection = createNewSetInstance(field);
            for (Object dbObject : dbSet) {
                if (ClassUtils.isWrappedClass(elementType)) {
                    collection.add(dbObject);
                } else {
                    collection.add(toObject((Document) dbObject, elementType));
                }
            }
            field.set(object, collection);
        } else {
            List<Document> dbList = document.get(fieldName, new ArrayList<>());
            List collection = createNewListInstance(field);
            for (Object dbObject : dbList) {
                if (ClassUtils.isWrappedClass(elementType)) {
                    collection.add(dbObject);
                } else {
                    collection.add(toObject((Document) dbObject, elementType));
                }
            }
            field.set(object, collection);
        }
    }

    private Set<?> createNewSetInstance(Field field) {
        if (SortedSet.class.isAssignableFrom(field.getType())) {
            return new TreeSet<>();
        } else if (LinkedHashSet.class.isAssignableFrom(field.getType())) {
            return new LinkedHashSet<>();
        } else {
            return new HashSet<>();
        }
    }

    private List<?> createNewListInstance(Field field) {
        if (Stack.class.isAssignableFrom(field.getType())) {
            return new Stack<>();
        } else if (Vector.class.isAssignableFrom(field.getType())) {
            return new Vector<>();
        } else if (LinkedList.class.isAssignableFrom(field.getType())) {
            return new LinkedList<>();
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * Set value for map attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    @SuppressWarnings("unchecked")
    private void setMapField(Object object, Field field, Document document) throws IllegalAccessException, ClassNotFoundException {
        String fieldName = BsonUtils.getDocumentFieldName(field);
        Document subDocument = (Document) document.get(fieldName);

        field.setAccessible(true);
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        Type type = parameterizedType.getActualTypeArguments()[1];
        Class<?> clazz = Class.forName(type.getTypeName());

        Map map = new HashMap();
        subDocument.entrySet().forEach(entry ->{
            String key = entry.getKey();
            Object value = entry.getValue();

            if (ClassUtils.isSimpleValue(value)) {
                map.put(key, value);
            } else {
                map.put(key, toObject((Document)value, clazz));
            }

        });
        field.set(object, map);
    }


    /**
     * /**
     * Set value for class attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     */
    private void setComplexValueField(Object object, Field field, Document document) throws IllegalAccessException {
        String fieldName = BsonUtils.getDocumentFieldName(field);
        Class<?> fieldClass = field.getType();
        Object subObject = fieldClass.cast(toObject((Document) document.get(fieldName), fieldClass));
        field.set(object, subObject);
    }
}
