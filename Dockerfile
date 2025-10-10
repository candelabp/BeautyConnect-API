FROM eclipse-temurin:17-jdk as build

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:17-jre

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]