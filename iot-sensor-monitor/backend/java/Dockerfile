# 1. Build stage
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/iot-backend-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
