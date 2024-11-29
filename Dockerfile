FROM openjdk:17
RUN apt-get update && apt-get install -y findutils
COPY build/libs/small-api-0.0.1-SNAPSHOT.jar /app/small-api.jar
ENTRYPOINT ["java", "-jar", "/app/small-api.jar"]
