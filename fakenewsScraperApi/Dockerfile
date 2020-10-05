FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD

MAINTAINER alexxsnjr

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/

RUN mvn package


FROM openjdk:11
EXPOSE 8090
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/fakenewsScraperApi-0.0.4-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "fakenewsScraperApi-0.0.4-SNAPSHOT.jar"]