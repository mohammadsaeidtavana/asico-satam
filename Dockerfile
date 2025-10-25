## Stage 1: Build the application
#FROM maven:3.8.6-eclipse-temurin-17 AS build
#
## Set the working directory
#WORKDIR /app
#
## Copy the pom.xml and download dependencies
#COPY pom.xml .
##RUN mvn dependency:go-offline
#
## Copy the source code and build the application
#COPY src ./src
##RUN mvn clean install
#
## Stage 2: Run the application
#FROM eclipse-temurin:17-jdk
#
## Set the working directory
#WORKDIR /app
#
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} hozeh.jar
## Copy the jar file from the build stage
##COPY --from=build /app/target/*.jar hozeh.jar
#
## Expose the application port
#EXPOSE 80
#
## Run the application
#ENTRYPOINT ["java", "-jar", "hozeh.jar"]
#


FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
#RUN mvn dependency:go-offline
COPY src/ ./src/
RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:17-jdk-alpine AS prod
RUN mkdir /app
COPY --from=builder /app/target/*.jar /app/app.jar
ENV SERVER_PORT=80
WORKDIR /app
EXPOSE 80
ENTRYPOINT ["java","-jar","/app/app.jar","--spring.profiles.active=prod"]