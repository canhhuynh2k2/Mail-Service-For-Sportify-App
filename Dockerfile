FROM openjdk:17

WORKDIR /app

COPY ./target/mailservice-0.0.1-SNAPSHOT.jar /app/mailservice-0.0.1-SNAPSHOT.jar

EXPOSE 8090
CMD ["java", "-jar", "mailservice-0.0.1-SNAPSHOT.jar"]