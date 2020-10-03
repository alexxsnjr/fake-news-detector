# Start with a base image containing Java runtime
FROM openjdk:11

# Add Maintainer Info
LABEL maintainer="alexxsnjr@gmail.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/fakenewsScraperApi-0.0.4-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} fakenewsScraperApi.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/fakenewsScraperApi.jar"]