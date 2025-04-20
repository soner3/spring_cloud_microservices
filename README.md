# Spring Cloud Microservices Architecture

This project demonstrates a cloud-native microservices architecture built using **Java**, **Spring Cloud**, and **Docker**. The system consists of four core microservices and a set of supporting infrastructure components to enable configuration management, service discovery, routing, authentication, and asynchronous messaging.

---

## Architecture Overview

### Core Microservices

- **product-service** – Manages product information and metadata.
- **recommendation-service** – Provides product recommendations.
- **review-service** – Manages customer reviews and ratings.
- **product-aggregator-service** – Aggregates data from the above three services to present a unified product view to the client.

### Supporting Infrastructure

- **Config Server** – Centralized configuration management.
- **Discovery Server (Eureka)** – Service registration and discovery.
- **API Gateway** – Routes incoming requests to the appropriate services.
- **RabbitMQ** – Message broker for asynchronous, event-driven communication.
- **Keycloak** – OAuth2-compliant identity and access management system.

---

## 🔄 Aggregation Flow

The `product-aggregator-service` acts as an orchestrator that:

1. Retrieves product details from `product-service`
2. Fetches associated recommendations from `recommendation-service`
3. Collects user reviews from `review-service`
4. Combines all data into a single response for clients

This design follows the **API Composition** pattern and enables clients to consume product-related data efficiently through a single endpoint.

---

## Getting Started

### Prerequisites

- Docker & Docker Compose installed
- Bash shell (for running scripts)

### 1. Build Docker Images

From the project root, execute:

```bash
./build-image.sh
```
### 2. Start Compose

From the project root, execute:

```bash
cd docker/prod
docker compose up -d
```

