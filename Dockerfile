

# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
# Copy only necessary files for build context
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage (lightweight)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/movie-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
