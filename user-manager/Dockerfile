# Use a base image with Java and Maven
FROM maven:3.9.9-amazoncorretto-21-alpine

ENV EUREKA_HOST="localhost"
ENV EUREKA_PORT="8761"

# Set the working directory inside the container
WORKDIR /app

# Copy the parent pom.xml from m321-shop to /app
COPY ./pom.xml ./pom.xml

# Copy the user-manager directory to the container's working directory
COPY ./user-manager /app/user-manager

# Set the working directory to user-manager
WORKDIR /app/user-manager

# Run maven to build the project
RUN mvn clean install -DskipTests

ENTRYPOINT ./docker-entrypoint.sh