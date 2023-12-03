FROM gradle:jdk8 as build
WORKDIR /app
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src
RUN gradle clean build -x test

FROM openjdk:8
COPY --from=0 /app/build/libs/CardsChallenge-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar", "--spring.config.location=classpath:/application.dev.properties"]