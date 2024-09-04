# Use an official Java runtime as a parent image
FROM eclipse-temurin:21-jre-alpine

# Add bash
RUN apk add --no-cache bash

# Set the working directory in the container
WORKDIR /app

# Add shell script that will wait for elastic-search to start gracefully
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

# Copy the project jar file to the container
COPY demo*.jar app.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
