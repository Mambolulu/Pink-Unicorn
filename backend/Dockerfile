FROM openjdk:17
LABEL authors="elias"
COPY build/libs/*.jar spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/spring-app.jar"]
