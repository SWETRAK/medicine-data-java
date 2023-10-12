FROM maven:3.9.4-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app
RUN mvn package -DskipTests

# Second stage: create a slim image
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/medicineData.jar /app.jar
ENV SPRING_PROFILES_ACTIVE="docker"
ENTRYPOINT ["java", "-jar", "/app.jar"]


#
#FROM eclipse-temurin:17-jdk-alpine
#COPY ./ /
##RUN ./mvnw install -DskipTests
#RUN ./mvnw package -DskipTests
#
#ENTRYPOINT ["java", "-jar", "/target/medicine-data-0.0.1-SNAPSHOT.jar"]