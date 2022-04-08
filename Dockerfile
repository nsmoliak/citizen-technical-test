FROM openjdk:8
COPY target/citizen-technical-test-*.jar citizen-technical-test.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "citizen-technical-test.jar"]