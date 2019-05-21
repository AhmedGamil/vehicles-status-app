docker build -t "config" ./config
docker build -t "registry" ./registry
docker build -t "gateway" ./gateway
docker build -t "customer-vehicle-service" ./customer-vehicle-service
docker build -t "vehicle-status-service" ./vehicle-status-service
docker-compose up