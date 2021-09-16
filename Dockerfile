FROM openjdk:11-slim

WORKDIR /home/pricer
COPY ./build/libs/pricer-0.0.1.jar pricer.jar

EXPOSE 8081
ENTRYPOINT ["java","-Dspring.profiles.active=%%SPRING.ACTIVE.PROFILE%%","-jar","pricer.jar"]