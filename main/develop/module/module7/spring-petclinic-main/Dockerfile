# Use the official Tomcat image as the base image
FROM tomcat:9-jdk11

# Remove default Tomcat apps to clear the webapps directory
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the .war from the Jenkins workspace into the Tomcat webapps directory
COPY main/develop/module/builders/web/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 for the Tomcat server
EXPOSE 8080


#----------------------------------------------
# Start from the official OpenJDK base image
FROM openjdk:17-jre

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
