# User-Service

The User-Service manages user data, including roles and credentials.

## Features
- User CRUD operations.
- Role management.
- H2 in-memory database for development.

## Endpoints
- `POST /users/signup`: Register a new user.
- `GET /users/identifier`: Fetch user by username or email.
- Standard CRUD operations: Create, Read, Update, Delete.

## Configuration
- **Port**: `9001`
- **Properties**:
    - `spring.application.name`: USER-SERVICE
    - `spring.datasource.url`: H2 in-memory database.

## Running the Service
1. Navigate to the `user-service` directory:
   ```bash
   cd user-service
   ```
2. Build and run the service:
   ```bash
   mvn spring-boot:run
   ```
3. Access the service at `http://localhost:9001`.

## Docker
- The `Dockerfile` builds a lightweight image for the service.
- The service is included in the `docker-compose.yml` configuration.

## Dependencies
- Spring Boot
- Spring Data JPA
- H2 Database
- Spring Security
