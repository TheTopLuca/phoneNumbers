FROM openjdk
ADD target/docker-spring-boot.jar docker-spring-boot.jar
EXPOSE 8080
ADD sample.db sample.db
ENTRYPOINT ["java", "-jar","docker-spring-boot.jar"]
