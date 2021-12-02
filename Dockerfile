FROM openjdk:11

COPY build.gradle .

ARG JAR_FILE=/build/libs/csg-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} testee.jar

ENTRYPOINT ["java","-jar","testee.jar"]
EXPOSE 8080
