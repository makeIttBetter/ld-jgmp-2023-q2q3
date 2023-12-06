# Start from the official OpenJDK base image
FROM openjdk:17-jdk

# Create a new user and group
RUN groupadd -r appuser && useradd -r -g appuser appuser

## Set working directory
#WORKDIR /app

# Copy the JAR file from the Jenkins workspace
COPY main/develop/module/module7/spring-petclinic-main/*.jar app.jar

# Switch to the non-root user
USER appuser

# Define the memory limit arguments (These can be overridden during container run)
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# Expose port 8080 for the application
EXPOSE 8080

# Define the entrypoint command to run the Spring Boot application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
