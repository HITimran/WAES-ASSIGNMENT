# WAES-ASSIGNMENT

# differ-for-testers
Ready-made project for a Tester/QA assignment.  

## The setup
- JAVA 8+ jdk must be [installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)  
- MAVEN must be [installed](https://maven.apache.org/download.cgi)  

## Running the application
From the project root folder:  
1. run `mvn clean test`  
2. run `mvn exec:java`  

The service runs on `localhost:8081` by default.

### Examples:
POST `http://localhost:8081/diffassign/v1/diff/3/left` with `"abcd1234"`  
POST `http://localhost:8081/diffassign/v1/diff/3/right` with `"abcd"`  
GET `http://localhost:8081/diffassign/v1/diff/3/` returns `{"type": "DIFFERENT_LENGTH"}`  
