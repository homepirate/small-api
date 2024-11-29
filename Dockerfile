FROM openjdk:17
COPY build/libs/small-api-0.0.1-SNAPSHOT.jar /app/small-api.jar
ENTRYPOINT ["java", "-jar", "/app/small-api.jar"]
