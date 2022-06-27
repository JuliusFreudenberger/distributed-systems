FROM maven:3.8-openjdk-11 AS build
WORKDIR /build
COPY . .
RUN mvn -q -DskipTests package

FROM openjdk:11-jre
WORKDIR /app
HEALTHCHECK --interval=12s --timeout=12s --start-period=10s CMD wget --no-verbose --tries=1 --spider http://localhost:8090/todos || exit 1
COPY --from=build /build/target/*.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]