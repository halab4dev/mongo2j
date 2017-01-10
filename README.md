## Overview & Installation
Mongo2J is a light weight and easy to use library to parse mongo document to java object and vice versa.

To start using Mongo2J, just download 
[mongo2j-1.0.0.jar](https://github.com/halab4dev/mongo2j/releases/download/v1.0.0/mongo2j-1.0.0.jar)
and import it to your project as a library.

## Guideline

* Entity class:
``` java
public class Developer {

    @DocumentId
    private String id;
    private String name;
    private int age;
    private Address address;
    @DocumentField(name = "skills")
    private List<String> languages;
...
}


public class Address {

    private int no;
    private String street;
    private String city;
...
}
```
* Annotations
  * `@DocumentId`: mark a class attribute as the document id.
  * `@DocumentField`: tell the parser to parse this attribute to specific document field (here is `skills`)
  
* Parsing
```java
    //Create java objects
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

    //Declare parser
    Parser parser = SimpleParser.getInstance();

    //Parse an object to mongo document
    Document document = parser.toDocument(developer);
```
The result should be:
```
{
    "_id" : ObjectId("507f191e810c19729de860ea"),
    "name" : "halab",
    "age" : 25,
    "address" : {
        "no" : 808,
        "street" : "Nguyen Khuyen",
        "city" : "Hanoi"
    },
    "skills" : [ 
        "Java", 
        "Javasccript"
    ]
}
```
To parse a document to java object, just simple:
```java
Developer dev = (Developer)parser.toObject(document, Developer.class);
```
