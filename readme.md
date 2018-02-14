##A Objective : 
Playground for all things reactive + java.

##Prerequisites
    * Java 8  

#Getting Started: 
## 1. To run tests & build the artifact: 
```sh
mvn clean package
```
## 2. To run : 
Go to the folder that has the artifact, e.g. target folder after building.
```sh
    java   -jar reactive-playground.jar
``` 
    
#Under the Hood:
This is a Spring SpringBoot application with Java. 
Have used spring-boot-social-twitter for twitter searches.
Undertow serving the application instead of default tomcat container.
  
  
### Finally we can also run this in a Docker container.
After building the artifact, we can build the image by : 
 ```sh
docker build -t reactive-testimg  . 
 ``` 
And Run by  
```sh
docker run -d -t --name reactive-app reactive-testimg 
 ```  
 
 https://github.com/spring-projects/spring-social-samples/blob/master/spring-social-showcase/src/main/java/org/springframework/social/showcase/ExceptionHandlingControllerAdvice.java