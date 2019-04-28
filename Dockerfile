#FROM java:8
FROM adoptopenjdk/openjdk11
LABEL maintainer="christmagro@gmail.com"
WORKDIR /app
COPY target/mobile_subscribers-1.0.0-SNAPSHOT.jar /app/mobile_subscribers.jar
ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","mobile_subscribers.jar"]