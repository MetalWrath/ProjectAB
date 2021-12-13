FROM openjdk:11-jdk
EXPOSE 5000
ARG JAR_FILE=build/libs/ProjectAB-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]