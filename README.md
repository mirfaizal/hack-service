## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Read-Only Files:
- src/test/*

## Data:
Example of a Item data JSON object:
```json
{
    "itemId":1,
    "itemName":"item_x",
     "itemEnteredByUser":"user_x",
    "itemEnteredDate":"2020-05-10T13:00:41.499",
    "itemBuyingPrice":50.0,
    "itemSellingPrice":55.0,
    "itemLastModifiedDate":"2020-05-10T13:00:41.498",
    "itemLastModifiedByUser":"user_y",
    "itemStatus":"AVAILABLE"
}
```

## Requirements:
Create a spring boot ORM/Hibernate based MVC project which provides the REST endpoints for the basic inventory management system. For this project, Inventory system contains only the single entity named `Item`.

You have to implement `/app/item` REST endpoint for following 8 operations.

`POST` request to `/app/item`:

* should accept POST requests at /app/item and item data as JSON body
* if the itemId exists in the database then it should return status code 400
* If the itemId doesn't exist in the database then it should insert and return the inserted item as response with status code 201
 
`PUT` request to `/app/item/{itemId}`:

* should accept PUT requests at /app/item/{itemId} and item data as JSON body where itemId is a path variable
* if the itemId exists in the database then it should update and return updated item as response with status code 200
* if the itemId doesn't exist in the database it should return status code 404

`DELETE` request to `/app/item/{itemId}`:

* should accept DELETE requests at /app/item/{itemId} where itemId is a path variable
* if the itemId exists in the database then it should delete and return status code 200
* if the itemId doesn't exist in the database it should return status code 400
 
`DELETE` requests at `/app/item`:

* should accept DELETE requests at /app/item
* should delete all the items from the database and return status code 200

`GET` request to `/app/item/{itemId}`:

* should accept GET requests at /app/item/{itemId} where itemId is a path variable
* if the itemId exists in the database then it should return item with status code 200
* if the itemId doesn't exist in the database it should return status code 404

`GET` request to `/app/item`:

* should accept GET requests at /app/item
* should return all the items from the database with return status code 200

`GET` request to `/app/item?itemStatus={status}&itemEnteredByUser={enteredBy}`:

* it should accept GET requests at /app/item?itemStatus={status}&itemEnteredByUser={enteredBy}
* it should return all the items having itemStatus=status and itemEnteredByUser=enteredBy, where status and enteredBy are request params, with status code 200
 
`GET` request to `/app/item?pageSize={pageSize}&page={page}&sortBy={sortByField}`:

* it should accept GET requests at /app/item?pageSize={pageSize}&page={page}&sortBy={sortByField}
* it should return the requested page by paginating with pageSize and sorted by sortBy field

Your task is to implement the above 8 endpoints in `ItemController` class and corresponding 
service methods in `ItemService` class so that it passes all the test cases when running the provided unit tests. 
The project by default supports the use of the H2 database. 
Implement the POST request to `/app/item` first because testing the other methods requires POST to work correctly.

## Commands
- run: 
```bash
mvn clean package; java -jar target/SpringBootORM-1.0.jar
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```
