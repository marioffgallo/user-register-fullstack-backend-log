FROM openjdk:11
LABEL mentainer="mario.felipe.gallo@gmail.com"
VOLUME /tmp
EXPOSE 9192
ARG JAR_FILE=target/user-register-fullstack-backend-log-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]