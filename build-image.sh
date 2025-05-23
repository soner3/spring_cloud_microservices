#!/bin/bash

BASE_DIR=$(pwd)

services=("product" "product-aggregator" "recommendation" "review")

version=v8

infrastructureServices=("configserver" "eurekaserver" "gatewayserver")

for service in "${services[@]}"; do
    (
        echo "Building docker images: $service" || exit;
        cd "$BASE_DIR/microservices/$service";
        ./mvnw clean package;
        docker build -t "soner9/$service:$version" . || exit;
        echo "Finished building $service";
    ) &

done 

for infrastructureService in "${infrastructureServices[@]}"; do
    (
        echo "Building docker images: $infrastructureService" || exit;
        cd "$BASE_DIR/$infrastructureService";
        ./mvnw clean package;
        docker build -t "soner9/$infrastructureService:$version" . || exit;
        echo "Finished building $infrastructureService";
    ) &
done
wait
echo "All services have been built successfully"