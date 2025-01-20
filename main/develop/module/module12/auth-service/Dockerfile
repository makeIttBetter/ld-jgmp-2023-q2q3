# --------------- STAGE 1: Build the JAR ---------------
FROM maven:3.9.3-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy only the POM first for dependency caching
COPY pom.xml ./

# Download dependencies based on the POM
RUN mvn dependency:go-offline -B

# Now copy the source code
COPY src/ ./src/

# Build the JAR (skip tests for faster builds if desired)
RUN mvn clean package -DskipTests

# --------------- STAGE 2: Run the JAR ---------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the JAR from the builder stage
COPY --from=builder /app/target/auth-service-1.0.0.jar auth-service.jar

# Expose the application's port
EXPOSE 9000

# Use environment variables from your .env (in docker-compose)
# so we do not hardcode them here.

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app/auth-service.jar"]
