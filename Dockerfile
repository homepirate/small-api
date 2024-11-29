FROM openjdk:17
COPY build/libs/small-api-0.0.1-SNAPSHOT.jar small-api.jar
ENTRYPOINT ["java", "-jar", "small-api.jar"]
