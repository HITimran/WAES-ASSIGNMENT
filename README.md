# WAES-ASSIGNMENT

# differ-for-testers
Ready-made project for a Tester/QA assignment.  

## The setup
- JAVA 8+ jdk must be [installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
- MAVEN must be [installed](https://maven.apache.org/download.cgi)  

## About Project


## Running the application
From the project root folder:  
1. run `mvn clean test`  
2. run `mvn exec:java`  

The service runs on `localhost:8081` by default.

### Examples:
POST `http://localhost:8081/diffassign/v1/diff/3/left` with `"abcd1234"`  
POST `http://localhost:8081/diffassign/v1/diff/3/right` with `"abcd"`  
GET `http://localhost:8081/diffassign/v1/diff/3/` returns `{"type": "DIFFERENT_LENGTH"}`  

### Suggestion and Improvement:

```
Scenario:
- GIVEN Right slide is successfully posted
- BUT Left slide remains empty
- WHEN Get request is fetch for above id 
- Then error thrown as "Left side contains no value."

Suggestion for above Scenario:-
- for vise-a-versa condition ,we need to implement message such as "Right side contains no value." 
Presently we see no such message error as 204 (No Content)
```

```
The way response code 
415 UNSUPPORTED_MEDIA_TYPE_JSON was handle with message as "Value in request body must be in JSON format."
400 BAD_REQUEST was handle with message as "Value in request body cannot be empty."
501 NOT_IMPLEMENTED was handle with message as "This side is not supported, please use either 'left' or 'right'."
415 BASE64Exception was handle with message as "Data in body not Base64 formatted."
404 NOT_FOUND was handle with message as "Id <<>> not initialized."
Same suppose to be handle for
200 OK 
500 REQUEST_FAILED
204 NO_CONTENT
404 NOT_FOUND doesnt throw nay error message when slide length exceeds it upperlimit boundary,negative slide number
```
