# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/DevOps_Project-1.0.0.jar /app/DevOps_Project-1.0.0.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "DevOps_Project-1.0.0.jar"]
#1
