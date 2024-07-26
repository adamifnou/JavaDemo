FROM gradle:8.5-jdk21 AS build

COPY . /backened/

WORKDIR /backend/


FROM openjdk:21

ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} backend/demo.jar

ENTRYPOINT ["java", "-jar", "/backend/demo.jar"]


#ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} demo.jar