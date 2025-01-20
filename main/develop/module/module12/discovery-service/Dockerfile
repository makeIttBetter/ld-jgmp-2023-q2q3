# --------------- STAGE 1: Build the JAR ---------------
FROM maven:3.9.3-eclipse-temurin-17 AS builder
WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline -B

COPY src/ ./src/
RUN mvn clean package -DskipTests

# --------------- STAGE 2: Run the JAR ---------------
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/target/discovery-service-1.0.0.jar discovery-service.jar
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/discovery-service.jar"]
