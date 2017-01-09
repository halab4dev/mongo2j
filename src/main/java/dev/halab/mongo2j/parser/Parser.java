package dev.halab.mongo2j.parser;

import com.mongodb.BasicDBList;
import org.bson.Document;

import java.util.Collection;

/**
 * A parse provides methods to parse a mongo document to a java class and vice versa
 *
 * @author Apollo
 */
public interface Parser {

    /**
     * Parse a java object to mongo document
     *
     * @param object java object
     * @return mongo document
     */
    Document toDocument(Object object);


    /**
     * Parse a java collection to mongo list
     *
     * @param collection java collection
     * @return mongo list
     */
    BasicDBList toDBList(Collection collection);


    /**
     * Parse a mongo document to a java object
     *
     * @param document mongo document
     * @param clazz class of java object
     * @return a java object can be cast to target class
     */
    Object toObject(Document document, Class clazz);
}
