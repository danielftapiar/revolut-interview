FROM gradle:7.6.1-jdk17  as cache
RUN mkdir -p /home/gradle/cache_home
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle clean build -i --stacktrace


FROM gradle:7.6.1-jdk17 as builder
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
EXPOSE 8080
WORKDIR /app
COPY . /app
RUN gradle bootJar

FROM openjdk:19-jdk-slim-buster as app
WORKDIR /app
COPY --from=builder /app/app.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]