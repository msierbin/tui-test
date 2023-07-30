# Start with a base image containing Java runtime
FROM openjdk:20-jdk-slim

# The application's jar file
ARG JAR_FILE=target/tui-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8080