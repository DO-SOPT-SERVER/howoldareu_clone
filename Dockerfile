FROM amazoncorretto:17

ARG JAR_FILE=build/libs/Server-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} Server-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","-XX:MaxRAMPercentage=75","/Server-0.0.1-SNAPSHOT.jar"]