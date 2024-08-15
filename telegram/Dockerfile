FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ARG JAR_FILE=target/*.jar
COPY /build/libs/telegram-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

