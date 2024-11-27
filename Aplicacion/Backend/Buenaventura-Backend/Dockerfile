FROM ubuntu:latest AS build
RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar --no-daemon

FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /app/build/libs/how-much-pay-api-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]