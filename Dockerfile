FROM openjdk:8-jdk-alpine

MAINTAINER Valdeci Junior

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 8080