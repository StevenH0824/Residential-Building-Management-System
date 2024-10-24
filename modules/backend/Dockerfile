FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/building-management-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]



