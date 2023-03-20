FROM gradle:7.4.2-jdk17  as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
WORKDIR /app
COPY . /app
RUN gradle clean build -x test -i --stacktrace


FROM gradle:7.4.2-jdk17 as builder
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
WORKDIR /app
COPY . /app
RUN gradle bootJar

FROM openjdk:17-jdk-slim-buster as app
WORKDIR /app
COPY --from=builder /app/build/revolut-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]