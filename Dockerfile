FROM maven:3.8.4-openjdk-17 as dependencies

WORKDIR /app

COPY pom.xml ./
COPY ./*.config ./

RUN mvn -B dependency:go-offline


FROM dependencies as build

COPY src ./src

RUN mkdir ./target

RUN mvn -B clean package


COPY .env .

ENV DB_URL=${DB_URL}
ENV DB_DRIVER=${DB_DRIVER}
ENV DB_USERNAME=${DB_USERNAME}
ENV DB_PASSWORD=${DB_PASSWORD}
ENV EMAIL_USERNAME=${EMAIL_USERNAME}
ENV PASS_EMAIL=${PASS_EMAIL}

FROM build as sonar 

RUN mvn sonar:sonar \
    -Dsonar.projectKey=dmx-solicitudes-service \
    -Dsonar.host.url=http://localhost:9009 \
    -Dsonar.login=553d9237bb2d49ad38ae5642813bd7

FROM openjdk:17-alpine as main

WORKDIR /opt/app

EXPOSE 8080

COPY --from=build /app/target/*.jar /opt/app/service.jar

RUN apk update && apk upgrade

ENTRYPOINT ["java","-jar","/opt/app/service.jar"]