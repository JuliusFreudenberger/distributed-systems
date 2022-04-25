FROM maven:3.8-openjdk-11 AS build
WORKDIR /build
COPY . .
RUN mvn -q package

FROM openjdk:11-jre
WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]