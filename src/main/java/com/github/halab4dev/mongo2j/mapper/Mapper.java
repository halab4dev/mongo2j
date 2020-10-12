package com.github.halab4dev.mongo2j.mapper;

import org.bson.Document;

import java.util.Collection;
import java.util.List;

/*
 *
 * @author halab
 */
public interface Mapper {

    Document toDocument(Object object);

    <T>T toObject(Document document, Class<T> type);
}
