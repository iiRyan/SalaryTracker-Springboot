FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the local target directory to the /app directory in the container
COPY target/salarytrackeraapi-0.0.1-SNAPSHOT.jar /app/salarytrackeraapi-0.0.1-SNAPSHOT.jar

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/salarytrackeraapi-0.0.1-SNAPSHOT.jar"]
