FROM gradle:jdk17-alpine AS builder
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle/
RUN chmod +x ./gradlew

RUN mkdir -p resource-service
COPY resource-service/build.gradle ./resource-service/

RUN ./gradlew :resource-service:dependencies --no-daemon

COPY resource-service/src ./resource-service/src

RUN ./gradlew :resource-service:assemble --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/resource-service/build/libs/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]