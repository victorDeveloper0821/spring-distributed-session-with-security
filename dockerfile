FROM openjdk:8-jdk-alpine

EXPOSE 8081

ADD target/docker-demo.jar docker-demo.jar

ENTRYPOINT ["java", "-jar" , "-DAPI_ENV=local" , "docker-demo.jar"]
