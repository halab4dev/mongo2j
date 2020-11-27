package com.github.halab4dev.mongo2j.mapper;

import com.github.halab4dev.mongo2j.annotation.BsonId;
import com.github.halab4dev.mongo2j.util.ClassUtils;
import com.github.halab4dev.mongo2j.util.Validator;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Field;
import java.util.*;

/*
 *
 * @author halab
 */
public class BsonSerializer extends BsonProcessor {

    private static final String ID = "_id";

    public Document toDocument(Object object) {
        if (Validator.isNull(object)) {
            return null;
        }

        Document document = new Document();

        Class<?> clazz = object.getClass();
        List<Field> fields = new ArrayList<>();
        List<Field> superClassFields = ClassUtils.getSuperClassField(clazz);
        List<Field> thisClassFields = ClassUtils.getClassField(clazz);
        fields.addAll(superClassFields);
        fields.addAll(thisClassFields);
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
    private void addDocumentField(Document document, Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        if (Validator.isNull(field.get(object))) {
            return;
        }

        if (isAnnotatedIgnored(field)) {
            return;
        }

        String fieldName = getDocumentFieldName(field);
        if (field.isAnnotationPresent(BsonId.class)) {
            BsonId idAnnotation = field.getDeclaredAnnotation(BsonId.class);
            Class<?> type = idAnnotation.value();
            if (type == ObjectId.class) {
                ObjectId objectId = createObjectId(field.get(object), field.getType());
                document.append(ID, objectId);
            } else {
                document.append(ID, field.get(object));
            }

        } else if(isAnnotatedObjectId(field)) {
            ObjectId objectId = createObjectId(field.get(object), field.getType());
            document.append(fieldName, objectId);

        } else if (ClassUtils.isSimpleValue(field)) {
            document.append(fieldName, field.get(object));

        } else if (ClassUtils.isDate(field)) {
            document.append(fieldName, field.get(object));

        } else if (ClassUtils.isCollection(field)) {
            document.append(fieldName, toDocumentList((Collection<?>) field.get(object)));

        } else if (ClassUtils.isMap(field)) {
            Document subDocument = toDocument((Map<?,?>) field.get(object));
            document.append(fieldName, subDocument);
        } else {
            Document subDocument = toDocument(field.get(object));
            document.append(fieldName, subDocument);
        }
    }

    private ObjectId createObjectId(Object value, Class<?> type) {
        if (type == ObjectId.class) {
            return (ObjectId)value;
        }
        if (type == String.class) {
            String id = (String) value;
            return new ObjectId(id);
        }
        throw new IllegalArgumentException("ID must be String or ObjectId type");
    }

    List<?> toDocumentList(Collection<?> collection) {
        if (collection == null) {
            return null;
        }

        if (collection.isEmpty()) {
            return Collections.emptyList();
        }

        List result = new ArrayList<>();

        for(Object object : collection) {
            if (ClassUtils.isStringOrWrappedClass(object)) {
                result.add(object);
            } else {
                Document document = toDocument(object);
                result.add(document);
            }
        }
        return result;
    }

    private Document toDocument(Map<?,?> map) {
        if (map == null) {
            return null;
        }
        Document document = new Document();
        map.forEach((key, value) -> {
            if (ClassUtils.isSimpleValue(value)) {
                document.append(key.toString(), value);
            } else {
                document.append(key.toString(), toDocument(value));
            }
        });
        return document;
    }
}
