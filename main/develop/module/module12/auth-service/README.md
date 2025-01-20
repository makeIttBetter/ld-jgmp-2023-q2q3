# Auth-Service

The Auth-Service handles authentication and issues JWT tokens.

## Features
- User authentication via username/email and password.
- Token-based authentication using JWT.
- Integration with User-Service for user management.

## Endpoints
- `POST /auth/login`: Login and receive a JWT.
- `POST /auth/signup`: Register a new user.
- `GET /auth/token/verify`: Verify the validity of a token.

## Configuration
- **Port**: `9000`
- **Properties**:
    - `spring.application.name`: AUTH-SERVICE
    - `jwt.secret`: Set via environment variables.

## Running the Service
1. Navigate to the `auth-service` directory:
   ```bash
   cd auth-service
   ```
2. Build and run the service:
   ```bash
   mvn spring-boot:run
   ```
3. Access the service at `http://localhost:9000`.

## Docker
- The `Dockerfile` builds a lightweight image for the service.
- The service is included in the `docker-compose.yml` configuration.

## Dependencies
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
