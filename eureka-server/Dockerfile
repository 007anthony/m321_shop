FROM maven:3.9.9-amazoncorretto-21-alpine

WORKDIR /app

COPY ./src ./src
COPY pom.xml pom.xml

WORKDIR /app

RUN mvn clean install -DskipTests

ENTRYPOINT ["mvn", "spring-boot:run"]