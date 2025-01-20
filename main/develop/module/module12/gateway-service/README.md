# Gateway-Service

The Gateway-Service is an API gateway that routes requests to other services and provides security using JWT authentication.

## Features
- JWT authentication.
- Load-balanced routing using Spring Cloud Gateway.
- Global request tracing with `X-Trace-ID`.

## Configuration
- **Port**: `8080`
- **Routes**:
    - `/auth/**`: Auth-Service
    - `/users/**`: User-Service
- **Security**:
    - Public paths: `/auth/**`, `/actuator/**`
    - Secured paths: All others.

## Running the Service
1. Navigate to the `gateway-service` directory:
   ```bash
   cd gateway-service
   ```
2. Build and run the service:
   ```bash
   mvn spring-boot:run
   ```
3. Use the Gateway endpoint at `http://localhost:8080`.

## Docker
- The `Dockerfile` builds a lightweight image for the service.
- The service is included in the `docker-compose.yml` configuration.

## Dependencies
- Spring Boot
- Spring Cloud Gateway
- Spring Security
