<<<<<<< HEAD
# Marathon Project

A Spring Boot application built with Kotlin for managing posters and courses.

## 🛠 Tech Stack

- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.4.4
- **Database**: MongoDB
- **Build Tool**: Gradle with Kotlin DSL
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Testing**: JUnit 5, Kotest, MockK
- **Container**: Docker

## 🏗 Project Structure

```
src/main/kotlin/com/marathon/marathon/
├── controller/     # REST API endpoints
├── service/        # Business logic
├── repository/     # Data access layer
├── entity/         # Domain models
├── dto/           # Data Transfer Objects
├── mapper/        # Object mappers
├── config/        # Configuration classes
└── exception/     # Custom exceptions and handlers
```

## 🚀 Getting Started

### Prerequisites

- JDK 21
- Docker and Docker Compose
- MongoDB (or use provided Docker container)

### Running Locally

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Start MongoDB using Docker Compose:
```bash
docker-compose up mongodb
```

3. Build and run the application:
```bash
./gradlew bootRun
```

### Running with Docker

Build and run the entire stack using Docker Compose:
```bash
docker-compose up --build
```

## 📚 API Documentation

Once the application is running, you can access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

## 🔧 Configuration

### MongoDB Configuration
- Host: localhost (or mongodb for Docker)
- Port: 27017
- Username: root
- Password: password

## 🧪 Testing

Run tests using Gradle:
```bash
./gradlew test
```

The project uses:
- JUnit 5 for unit testing
- Kotest for behavior-driven development
- MockK for mocking in Kotlin
- Spring MockK for Spring Boot test utilities

## 🛡️ Features

- RESTful API endpoints for poster management
- MongoDB integration for data persistence
- Exception handling with custom responses
- Swagger API documentation
- Docker containerization
- Comprehensive test coverage

## 🔍 API Endpoints

### Poster Management
- `GET /api/posters` - Get all posters
- `GET /api/posters/{posterId}` - Get a specific poster
- `POST /api/posters` - Create a new poster
- `PUT /api/posters/{posterId}` - Update a poster
- `DELETE /api/posters/{posterId}` - Delete a poster
=======
# marathon
마라톤 정보 공유 서비스


# CI / CD
- develop branch push or PR => dev server 배포 진행
>>>>>>> 7abee8886c5c43381b892b94b247c98c2472d40e
