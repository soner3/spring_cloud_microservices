#!/bin/bash

BASE_DIR=$(pwd)

services=("product" "product-aggregator" "recommendation" "review")

for service in "${services[@]}"; do
    (
        echo "Building docker images: $service" || exit;
        cd "$BASE_DIR/microservices/$service";
        ./mvnw clean package spring-boot:build-image || exit;
        echo "Finished building $service";
    ) &

done
wait
echo "All services have been built successfully"