package dev.halab.mongo2j;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.halab.mongo2j.parser.Parser;
import dev.halab.mongo2j.parser.SimpleParser;
import org.bson.Document;

/**
 * @author Apollo
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Address address = new Address();
        address.setNo(808);
        address.setStreet("Nguyen Khuyen");
        address.setCity("Hanoi");

        Developer developer = new Developer();
        developer.setId("507f191e810c19729de860ea");
        developer.setName("halab");
        developer.setAge(25);
        developer.setAddress(address);
        developer.getLanguages().add("Java");
        developer.getLanguages().add("Javasccript");

        Parser parser = SimpleParser.getInstance();

        Document document = parser.toDocument(developer);
        System.out.println(document);

        Developer clone = (Developer) parser.toObject(document, Developer.class);
        System.out.println(developer);
        System.out.println(clone);
    }
}
