FROM openjdk:17

EXPOSE 8080

ADD backend/target/framefiesta.jar app.jar

CMD ["sh", "-c", "java -jar /app.jar"]
