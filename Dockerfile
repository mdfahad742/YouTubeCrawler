FROM amazoncorretto:11
LABEL maintainer="md.fahad742@gmail.com"
VOLUME /main-app
ADD target/crawler-docker.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
