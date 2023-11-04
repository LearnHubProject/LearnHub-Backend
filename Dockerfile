FROM amazoncorretto:17-alpine

WORKDIR /app

COPY build/libs/LearnHub-Backend.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]