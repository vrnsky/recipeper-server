FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /*.jar
ENTRYPOINT ["java","-jar","/target/"]