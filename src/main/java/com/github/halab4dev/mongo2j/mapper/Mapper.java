package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;

/*
 *
 * @author halab
 */
public interface Mapper {

    /**
     * Serialize java object to bson document
     * @param object java object
     * @return bson document
     */
    Document toDocument(Object object);

    /**
     * Deserialize bson document to java object
     * @param document bson document
     * @param type output class
     * @return java object
     */
    <T>T toObject(Document document, Class<T> type);
}
