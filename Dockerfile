FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Bram Houben
# Refer to Maven build -> finalName
ARG JAR_FILE=target/Fun4Backend-0.0.1-SNAPSHOT.jar


# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar



# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8095

