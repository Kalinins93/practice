FROM alpine
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN apk update && apk add maven
RUN mvn liquibase:update