# Brewery Tour 🍻

Brewery Tour is a booking and ordering system for craft breweries. It allows users to book tours of the beer-making facilities and purchase limited-edition beers.

## Features 💪

- RESTful API developed with Spring Boot
- JWT authentication to secure requests
- PostgreSQL database running in Docker containers
- Automated deployments to the cloud with CI/CD
- Level 3 Richardson Maturity Model with HATEOAS
- Swagger API documentation

## Architecture 🏗️

Brewery Tour follows a clean hexagonal architecture with:

- RESTful Spring Boot API to expose endpoints
- Application services for business logic
- Repositories and entities for database persistence
- SQL scripts for creating brewery, tour tables, etc
- Docker Compose configuration for the database

## Getting Started 🚀

### Prerequisites

- Java 21
- Maven
- Docker
- fly.io account

### Local Execution

Bring up database:
```
docker-compose up -d
```
Run Spring Boot app:

For Unix, (macOS and Linux)
```
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments='-DDB_URL=jdbc:postgresql://localhost:5432/brewery_tour -DDB_USER=brewery_user -DDB_PASS=Xy36Bic92!'
```

For Windows
```
./mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments='-DDB_URL=jdbc:postgresql://localhost:5432/brewery_tour -DDB_USER=brewery_user -DDB_PASS=Xy36Bic92!'
```
The app will be available on http://localhost:8080.

### Deployment

Configure fly.io account...

## Expressions of Gratitude 🎁

Comments and stars are always welcome!