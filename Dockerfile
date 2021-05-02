FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /*.jar
ENTRYPOINT ["java","-jar","/recipeper_server-1.0-SNAPSHOT.jar"]