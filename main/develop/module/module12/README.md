# Secure Microservices Architecture with Spring Boot and Docker

This project implements a secure, modular microservices architecture using Spring Boot, Spring Cloud, Docker, and JWT-based authentication. It includes the following services:

## Services
1. **Discovery-Service**: Eureka server for service registration and discovery.
2. **Gateway-Service**: API gateway for routing and securing requests.
3. **Auth-Service**: Authentication service issuing JWT tokens.
4. **User-Service**: Manages user data and roles.

## Features
- JWT authentication with HttpOnly cookies.
- Centralized service discovery using Eureka.
- API Gateway for routing and load balancing.
- Secure communication and traceable logging.

## Prerequisites
- Java 17
- Maven 3.9+
- Docker and Docker Compose
- Environment variables in a `.env` file (refer to each service README for details).

## Running the Application
1. Clone the repository.
2. Navigate to the root directory.
3. Run the following command to start all services:
   ```bash
   docker-compose up --build
   ```
4. Access the services:
    - Gateway: `http://localhost:8080`
    - Auth-Service: `http://localhost:9000`
    - User-Service: `http://localhost:9001`
    - Discovery-Service: `http://localhost:8761`

## Additional Information
Each service has its own README file detailing its functionality, endpoints, and configurations.
