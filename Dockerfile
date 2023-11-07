# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file into the container
COPY target/DevOps_Project-2.2.jar /app/DevOps_Project-2.1.jar

# Expose the port your Spring Boot application will run on
EXPOSE 8085

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "DevOps_Project-2.1.jar"]
#1
