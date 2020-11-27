# Change Log
## 2.2.0
- [Feature] Ignore serialize/deserialize with `@BsonIgnore`  
- [Bug] Ignore serialize/deserialize synthetic fields

## 2.1.0
- [Feature] Support object id filed with `@BsonProperty(isObjectId = true)`
- [Bug] Ignore serialize static final field

## 2.0.0
- [Dependency] Upgrade mongo java driver to `3.10.2`
- [Feature] Support new type `Document`
- [Feature] Remove support of `DBObject`
- [Bug] Fix serialize/deserialize with collection field
- [Bug] Fix serialize/deserialize with map field
- [Bug] Fix serialize/deserialize with date field