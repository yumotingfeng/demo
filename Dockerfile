FROM openjdk:8-jdk-alpine
ADD demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/demo.jar"]
