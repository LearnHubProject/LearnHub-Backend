FROM amazoncorretto:17-alpine

WORKDIR /app

COPY build/libs/LearnHub-Backend.jar app.jar
COPY entrypoint.sh entrypoint.sh

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "entrypoint.sh"]
