FROM java:8-jre
MAINTAINER Ahmed Gamil <ahmed.gamil@gmail.com>

ADD ./target/vehicle-status-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/vehicle-status-service.jar"]

EXPOSE 8081