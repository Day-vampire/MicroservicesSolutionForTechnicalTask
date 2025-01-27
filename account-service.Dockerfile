FROM jelastic/maven:3.9.5-openjdk-21 AS build

WORKDIR /app

COPY account-service/pom.xml .
COPY account-service/src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar /app/*.jar

CMD ["java", "-jar", "/app/*.jar"]