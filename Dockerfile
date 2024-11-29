FROM openjdk:17
COPY build/libs/small-api.jar /app/small-api.jar
ENTRYPOINT ["java", "-jar", "/app/small-api.jar"]
