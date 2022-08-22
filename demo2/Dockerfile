FROM openjdk:16
RUN mkdir /usr/src/my_app_directory
WORKDIR /usr/src/my_app_directory
COPY /target/Demo-0.0.1-SNAPSHOT.jar application.jar
COPY src/main/resources/application.properties application.properties
ENTRYPOINT ["java", "-jar", "application.jar","--spring.config.location=application.properties"]