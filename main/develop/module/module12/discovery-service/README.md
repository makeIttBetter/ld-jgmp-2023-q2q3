# Discovery-Service

The Discovery-Service is a Eureka server that enables service registration and discovery for the microservices in the architecture.

## Features
- Centralized service registry.
- Dynamic discovery of microservices.
- High availability (when deployed in a cluster).

## Configuration
- **Port**: `8761`
- **Properties**:
    - `spring.application.name`: DISCOVERY-SERVICE
    - `eureka.client.register-with-eureka`: false
    - `eureka.client.fetch-registry`: false

## Running the Service
1. Navigate to the `discovery-service` directory:
   ```bash
   cd discovery-service
   ```
2. Build and run the service:
   ```bash
   mvn spring-boot:run
   ```
3. Access the Eureka dashboard at `http://localhost:8761`.

## Docker
- The `Dockerfile` builds a lightweight image for the service.
- The service is included in the `docker-compose.yml` configuration.

## Dependencies
- Spring Boot
- Spring Cloud Netflix Eureka
