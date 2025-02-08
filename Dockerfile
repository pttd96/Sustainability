# Use an official Java 17 runtime as a parent image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy Gradle build output (assuming you use Gradle)
COPY build/libs/Sustainability-1.0.0.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
