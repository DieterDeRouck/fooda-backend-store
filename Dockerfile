FROM openjdk:11
MAINTAINER Dieter De Rouck <dieterderouck@hotmail.com>
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} store-backend.jar
ENTRYPOINT ["java","-jar","/store-backend.jar"]