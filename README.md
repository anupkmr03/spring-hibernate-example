# Application

##  Description
You should be able to start the example application by executing com.test.ServerApplicantTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Database H2 (In-Memory)
* Maven


You should be aware of the following conventions while you are working on this exercise:

 * All new entities should have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
 	* DataTransferObjects: Objects which are used for outside communication via the API
    * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
    * Service: Implements the business logic and handles the access to the DataAccessObjects.
    * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
    * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment is a good choice, but it's up to you how you are testing your code.
 * Feel free to use Java as well as Kotlin
 * We do provide code formatter for IntelliJ IDEA and Eclipse in the etc folder

You should commit into a local git repository and include the git repository (.git/) in the upload.

_NOTE: Please DO NOT publish the project, e.g. by uploading it to GitHub or the like!_

---


## Task 1
 * Write a new Controller for maintaining cars (CRUD).
   * Decide on your own how the methods should look like.
   * Entity Car: Should have at least the following characteristics: license_plate, seat_count, convertible, rating, engine_type (electric, gas, ...)
   * Entity Manufacturer: Decide on your own if you will use a new table or just a string column in the car table.
 * Extend the DriverController to enable drivers to select a car they are driving with.
 * Extend the DriverController to enable drivers to deselect a car.
 * Extend the DriverDo to map the selected car to the driver.
 * Add example data to resources/data.sql

## Solution Task 1
* New Controller added for Cars as well as manufacturer.
    * Separate Table to maintain the manufacture and maintained by Many-to-One relationship.
* One to One mapping for car and its driver (selection/de-selection)
    <br>
    
        Note : manufacture need to be added first before adding car
        Request for creating a Car:
        
        ```
        {
          "convertible": true,
          "engineType": "GAS",
          "id": 0,
          "licensePlate": "string",
          "manufacturerDTO": {
            "id": 3
          },
          "model": "string",
          "rating": 1.5,
          "seatCount": 4
        }
      ```
      
      to get the manufacture id hit get all manufacture
* Query for car and manufacturer added in data.sql 

---


## Task 2
First come first serve: A car can be selected by exactly one ONLINE Driver. If a second driver tries to select a already used car you should throw a CarAlreadyInUseException.

## Solution Task 2 
Handled by UniqueConstraint DB feature

---


## Task 3
Imagine a driver management frontend that is used internally by FREE NOW employees to create and edit driver related data. For a new search functionality, we need an endpoint to search for drivers. It should be possible to search for drivers by their attributes (username, online_status) as well as car characteristics (license plate, rating, etc).

* implement a new endpoint for searching or extend an existing one
* driver/car attributes as input parameters
* return list of drivers

## Solution 3
Added a generic solution for all type of search requirements

```
    Search Request 
    
    {
        "carFilters": {
          "SEAT_COUNT" : {"operator":"EQUAL", "value":"4"}
        },
        "driverFilters": {
           "STATUS" : {"operator":"EQUAL", "value":"ONLINE"}
        }
    }
    
    One or both filter can be used at same time
    
```

---


## Task 4
This task is _voluntarily_, if you can't get enough of hacking tech challenges, implement security.
Secure the API so that authentication is needed to access it. The details are up to you.

Please include instructions how to authenticate/login, so that we can test the endpoints you implemented!

## Solution 4

Spring Jwt token based security is used to secure the API. 
Whitelisted some swagger Endpoints to see the Swagger UI.

Steps for Authentication : 
1. Create a Jwt Token by using username as "test" and password "test"
In response it returns authentication token. (swagger UI have example)
<br>
Note : In case of changing username and password use this link to encrypt the password for dummy user : 
https://www.browserling.com/tools/bcrypt and update same in java code as its dummy user we need to update it manually. 
2. Use the same authentication token to access the API by passing the token in header of all API. 
Screenshot added for reference in resource folder.
---
Test Case added for service layer only <br>
Note : data.sql required to execute all test cases successfully 
```
RunAllTests to "Trigger all test cases in one go"
``` 
---
