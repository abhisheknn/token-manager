FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} token-manager.0.0.1.jar
ADD keyserverstore.keystore ./
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/token-manager.0.0.1.jar"]
