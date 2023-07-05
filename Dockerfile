FROM openjdk:17-alpine

COPY . /app

COPY .env /app

WORKDIR /app

ARG ENV_FILE=.env
RUN if [ -f $ENV_FILE ]; then \
      export $(cat $ENV_FILE | xargs) \
;fi

RUN mvn sonar:sonar \
    -Dsonar.projectKey=dmx-solicitudes-service \
    -Dsonar.host.url=http://localhost:9009 \
    -Dsonar.login=553d9237bb2d49ad38ae5642813bd7

FROM openjdk:17-alpine

COPY --from=builder /app/target/dmx-solicitudes-service.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "/dmx-solicitudes-service.jar"]
