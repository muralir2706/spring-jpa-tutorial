FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/spring-jpa-tutorial-0.0.1-SNAPSHOT.jar /app/spring-jpa-tutorial-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "spring-jpa-tutorial-0.0.1-SNAPSHOT.jar"]