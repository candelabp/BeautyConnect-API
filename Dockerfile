FROM gradle:8.7-jdk17-alpine AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle clean build -x test --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]