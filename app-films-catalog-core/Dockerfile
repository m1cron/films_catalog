# Build
FROM gradle:6.5-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

# Run
FROM openjdk:11
EXPOSE 8081
RUN mkdir /app
COPY --from=build /home/gradle/src/service/build/libs/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]