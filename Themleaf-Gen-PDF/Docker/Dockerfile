FROM openjdk:21-slim

COPY app.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "-Dcom.sun.management.jmxremote", "-Dcom.sun.management.jmxremote.port=12345", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.registry.ssl=false", "/app/app.jar"]