FROM maven:3.9.9-amazoncorretto-21-alpine

WORKDIR /app

COPY ./user-manager /app/user-manager

WORKDIR /app/user-manager

RUN mvn clean install -DskipTests

ENTRYPOINT ["mvn", "spring-boot:run"]