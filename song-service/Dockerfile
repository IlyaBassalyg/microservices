FROM gradle:jdk17-alpine AS builder
WORKDIR /app

COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle/
RUN chmod +x ./gradlew

RUN mkdir -p song-service
COPY song-service/build.gradle ./song-service/

RUN ./gradlew :song-service:dependencies --no-daemon

COPY song-service/src ./song-service/src

RUN ./gradlew :song-service:assemble --no-daemon -x test

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=builder /app/song-service/build/libs/*.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]