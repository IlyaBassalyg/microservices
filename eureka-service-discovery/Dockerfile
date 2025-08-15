FROM gradle:jdk17-alpine AS builder
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle/
RUN chmod +x ./gradlew

RUN mkdir -p eureka-service-discovery
COPY eureka-service-discovery/build.gradle ./eureka-service-discovery/

RUN ./gradlew :eureka-service-discovery:dependencies --no-daemon

COPY eureka-service-discovery/src ./eureka-service-discovery/src

RUN ./gradlew :eureka-service-discovery:assemble --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/eureka-service-discovery/build/libs/*.jar app.jar

EXPOSE 8761

CMD ["java", "-jar", "app.jar"]