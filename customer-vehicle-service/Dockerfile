FROM java:8-jre
MAINTAINER Ahmed Gamil <ahmed.gamil@gmail.com>

ADD ./target/customer-vehicle-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/customer-vehicle-service.jar"]

EXPOSE 6000