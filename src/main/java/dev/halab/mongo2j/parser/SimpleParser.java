package dev.halab.mongo2j.parser;

import com.mongodb.BasicDBList;
import dev.halab.mongo2j.annotation.DocumentField;
import dev.halab.mongo2j.annotation.DocumentId;
import dev.halab.mongo2j.util.ClassUtils;
import dev.halab.mongo2j.util.Validator;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * A class implements parsing methods in simple way:
 * <ul>
 * <li>The attribute which is primitive type or wrapped class will be parse directly to a key-value pair,
 * with key is attribute name (or name marked by DocumentField annotation) and value is value of this attribute.</li>
 * <li>The attribute which is collection will be parse to mongo list</li>
 * <li>The attribute which is a map or a class wil be parse to sub document</li>
 * </ul>
 *
 * @author Apollo
 */
public class SimpleParser implements Parser {

    private static final String ID = "_id";

    private static final SimpleParser INSTANCE = new SimpleParser();

    private SimpleParser() {

    }

    public static SimpleParser getInstance() {
        return INSTANCE;
    }

    public Document toDocument(Object object) {

        if (Validator.isNull(object)) {
            return null;
        }

        Document document = new Document();

        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                addDocumentField(document, field, object);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return document;
    }


    /**
     * Add document field
     *
     * @param document document
     * @param field    filed of java class
     * @param object   instance of java class
     */
    @SuppressWarnings("unchecked")
    private void addDocumentField(Document document, Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        if (Validator.isNull(field.get(object))) {
            return;
        }

        String fieldName = getDocumentFieldName(field);
        if (field.isAnnotationPresent(DocumentId.class)) {
            ObjectId objectId = createObjectId(field.get(object), field.getType());
            document.append(ID, objectId);

        } else if (ClassUtils.isSimpleValue(field)) {
            document.append(fieldName, field.get(object));

        } else if (ClassUtils.isCollection(field)) {
            document.append(fieldName, toDBList((Collection) field.get(object)));

        } else if (ClassUtils.isMap(field)) {
            Document subDocument = new Document((Map) field.get(object));
            document.append(fieldName, subDocument);
        } else {
            Document subDocument = toDocument(field.get(object));
            document.append(fieldName, subDocument);
        }
    }


    /**
     * Get mongo document field name
     *
     * @param field class attribute
     * @return mongo document field name
     */
    private String getDocumentFieldName(Field field) {
        return field.isAnnotationPresent(DocumentField.class) ?
                field.getDeclaredAnnotation(DocumentField.class).name() : field.getName();
    }


    /**
     * Create object id
     *
     * @param id      id of class marked as id
     * @param idClass class of id field
     * @return object id
     */
    @SuppressWarnings("unchecked")
    private ObjectId createObjectId(Object id, Class idClass) {
        if (!Validator.isValidIdType(idClass)) {
            throw new IllegalArgumentException("DocumentId field must be Date or String");
        }

        if (idClass.isAssignableFrom(String.class)) {
            String string = (String) id;
            if (!ObjectId.isValid(string)) {
                throw new IllegalArgumentException("DocumentId string value must be a hex string");
            }
            return new ObjectId(string);

        } else {
            Date date = (Date) id;
            return new ObjectId(date);
        }
    }


    public BasicDBList toDBList(Collection collection) {
        BasicDBList dbList = new BasicDBList();
        if (Validator.isNotEmpty(collection)) {
            for (Object element : collection) {
                if (ClassUtils.isWrappedClass(element)) {
                    dbList.add(element);
                } else {
                    Document document = toDocument(element);
                    dbList.add(document);
                }
            }
        }
        return dbList;
    }


    public Object toObject(Document document, Class clazz) {
        if (Validator.isNull(document)) {
            return null;
        }

        Object object;
        try {
            object = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                setObjectField(object, field, document);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return object;
    }


    /**
     * Set object field
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     * @throws InstantiationException when can not create class instance
     */
    private void setObjectField(Object object, Field field, Document document) throws IllegalAccessException, InstantiationException {
        field.setAccessible(true);

        if (field.isAnnotationPresent(DocumentId.class)) {
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
        ObjectId objectId = (ObjectId) document.get(ID);
        if (Validator.isNull(objectId)) {
            return;
        }

        if (field.getType().isAssignableFrom(String.class)) {
            field.set(object, objectId.toString());
        } else if (field.getType().isAssignableFrom(Date.class)) {
            field.set(object, objectId.getDate());
        } else {
            throw new IllegalArgumentException("DocumentId field must be Date or String");
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
        String fieldName = getDocumentFieldName(field);
        if (Validator.isNull(document.get(fieldName))) {
            return;
        }

        Class fieldClass = field.getType();
        switch (fieldClass.getSimpleName()) {
            case STRING:
                field.set(object, document.getString(fieldName));
                break;
            case PRIMITIVE_CHARACTER:
            case CHARACTER:
                field.set(object, document.getString(fieldName).charAt(0));
                break;
            case PRIMITIVE_BOOLEAN:
            case BOOLEAN:
                field.set(object, document.getBoolean(fieldName));
                break;
            case PRIMITIVE_BYTE:
            case BYTE:
                field.set(object, document.getInteger(fieldName).byteValue());
                break;
            case PRIMITIVE_SHORT:
            case SHORT:
                field.set(object, document.getInteger(fieldName).shortValue());
                break;
            case PRIMITIVE_INTEGER:
            case INTEGER:
                field.set(object, document.getInteger(fieldName));
                break;
            case PRIMITIVE_LONG:
            case LONG:
                field.set(object, document.getInteger(fieldName).longValue());
                break;
            case PRIMITIVE_FLOAT:
            case FLOAT:
                field.set(object, document.getDouble(fieldName).floatValue());
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
     * @throws InstantiationException when can not create class instance
     */
    @SuppressWarnings("unchecked")
    private void setCollectionField(Object object, Field field, Document document) throws IllegalAccessException, InstantiationException {
        String fieldName = getDocumentFieldName(field);
        BasicDBList dbList = (BasicDBList) document.get(fieldName);
        Collection collection = ((Collection) field.get(object));

        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        Class<?> elementType = (Class<?>) parameterizedType.getActualTypeArguments()[0];

        for (Object dbObject : dbList) {
            if (ClassUtils.isWrappedClass(elementType)) {
                collection.add(dbObject);
            } else {
                collection.add(toObject((Document) dbObject, elementType));

            }
        }
    }


    /**
     * Set value for map attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     * @throws InstantiationException when can not create class instance
     */
    @SuppressWarnings("unchecked")
    private void setMapField(Object object, Field field, Document document) throws IllegalAccessException, InstantiationException {
        String fieldName = getDocumentFieldName(field);
        Document subDocument = (Document) document.get(fieldName);
        Map map = ((Map) field.get(object));
        map.putAll(subDocument);
    }


    /**
     * /**
     * Set value for class attribute
     *
     * @param object   java object
     * @param field    class attribute
     * @param document mongo document
     * @throws IllegalAccessException when can not access class attribute
     * @throws InstantiationException when can not create class instance
     */
    private void setComplexValueField(Object object, Field field, Document document) throws InstantiationException, IllegalAccessException {
        String fieldName = getDocumentFieldName(field);
        Class fieldClass = field.getType();
        Object subObject = fieldClass.cast(toObject((Document) document.get(fieldName), fieldClass));
        field.set(object, subObject);
    }
}
