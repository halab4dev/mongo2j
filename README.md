![GitHub Workflow Status](https://img.shields.io/github/workflow/status/halab4dev/mongo2j/Java%20CI%20with%20Maven)
[![Coverage Status](https://coveralls.io/repos/github/halab4dev/mongo2j/badge.svg?branch=master)](https://coveralls.io/github/halab4dev/mongo2j?branch=master)
![GitHub last commit](https://img.shields.io/github/last-commit/halab4dev/mongo2j)

## Overview & Installation
Mongo2J is a light weight and easy to use library to map mongo document to java object and vice versa.

To start using Mongo2J, add dependency to `pom.xml` file:
```xml
<dependency>
  <groupId>com.github.halab4dev</groupId>
  <artifactId>mongo2j</artifactId>
  <version>2.2.0</version>
</dependency>
```

## Guideline

* Entity class:
``` java
public class Developer {

    @BsonId
    private String id;
    private String name;
    private int age;
    private Address address;
    @BsonProperty("skills")
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
  * `@BsonId`: mark a class attribute as the document id.
  * `@BsonProperty`: tell the parser to parse this attribute to specific document field (here is `skills`)
  
* Mapping
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

    //Declare mapper
    Mapper mapper = new DefaultMapper();

    //Map an object to mongo document
    Document document = mapper.toDocument(developer);
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
To map a document to java object, just simple:
```java
Developer dev = mapper.toObject(document, Developer.class);
```
