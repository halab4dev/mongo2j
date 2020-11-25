package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;

/*
 *
 * @author halab
 */
public class DefaultMapper implements Mapper {

    private BsonSerializer serializer = new BsonSerializer();
    private BsonDeserializer deserializer = new BsonDeserializer();

    @Override
    public Document toDocument(Object object) {
        return serializer.toDocument(object);
    }

    @Override
    public <T> T toObject(Document document, Class<T> type) {
        return deserializer.toObject(document, type);
    }
}
