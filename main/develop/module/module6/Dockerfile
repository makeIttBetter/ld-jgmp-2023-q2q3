# Use the official Tomcat image as the base image
FROM tomcat:9-jdk11

# Remove default Tomcat apps to clear the webapps directory
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the .war from the Jenkins workspace into the Tomcat webapps directory
COPY webapp/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080 for the Tomcat server
EXPOSE 8080
