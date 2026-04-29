# 📚 Bookstore E-Commerce Microservices

A **production-ready backend system** for a bookstore e-commerce platform, built using **Spring Boot Microservices Architecture**. This project demonstrates modern backend engineering practices including **service decomposition, API Gateway, centralized configuration, JWT authentication, and Docker-based deployment**.

---

## 🚀 Project Overview

This project transforms a traditional monolithic backend into a **scalable microservices architecture**.

Each service:

* Owns its own database (Database-per-Service pattern)
* Communicates via REST APIs
* Is independently deployable
* Registers with a service registry (Eureka)

---

## 🏗️ System Architecture

```
Client → API Gateway → Microservices → Database
```

### 🔑 Core Components

| Component     | Port | Description               |
| ------------- | ---- | ------------------------- |
| API Gateway   | 8080 | Routing, JWT validation   |
| Eureka Server | 8761 | Service discovery         |
| Config Server | 8888 | Centralized configuration |

---

## 🧩 Microservices Implemented

| Service              | Port | Description                      |
| -------------------- |------| -------------------------------- |
| User Service         | 8081 | Authentication & user management |
| Product Service      | 8083 | Book catalog & inventory         |
| Cart Service         | 8084 | Shopping cart (Redis)            |
| Order Service        | 8085 | Order management                 |
| Notification Service | 8086 | Email/SMS events                 |

---

## 🛠️ Tech Stack

### Backend

* Java 17 / 21
* Spring Boot 3.x
* Spring Cloud
* Spring Security (JWT)
* Spring Data JPA

### Infrastructure

* Netflix Eureka
* Spring Cloud Gateway
* Spring Cloud Config

### Database

* PostgreSQL
* Redis

### Messaging (optional extension)

* Apache Kafka

### DevOps

* Docker
* Docker Compose
* Kubernetes (optional)

---

## 📂 Project Structure

```
bookstore-microservices/
│
├── api-gateway/
├── eureka-server/
├── config-server/
│
├── services/
│   ├── user-service/
│   ├── product-service/
│   ├── cart-service/
│   ├── order-service/
│   └── notification-service/
│
├── infrastructure/
│   ├── docker-compose.yml
│
├── common-lib/
└── README.md
```

---

# 🔐 User Service (Core Highlight)

## Features

* User Registration
* Login with JWT Authentication
* Password Encryption (BCrypt)
* Role-based (USER / ADMIN)
* Exception Handling
* Swagger API Documentation

---

## 🔑 Authentication Flow

```
Register → Store hashed password
Login → Validate password → Generate JWT
Gateway → Validate JWT → Allow request
```

---

## 📌 API Endpoints

### 🔐 Auth APIs

#### Register

```
POST /auth/register
```

```json
{
  "name": "Akhil",
  "email": "akhil@gmail.com",
  "password": "1234"
}
```

---

#### Login

```
POST /auth/login
```

```json
{
  "email": "akhil@gmail.com",
  "password": "1234"
}
```

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

---

### 👤 User APIs

```
GET     /users
GET     /users/{id}
POST    /users
DELETE  /users/{id}
```

---

# 🔐 JWT Implementation

* Token contains:

    * Subject (email)
    * Role (USER / ADMIN)
* Used in API Gateway for authentication

---

# ⚙️ Configuration Server

Centralized configs:

```properties
jwt.secret=your-secret-key
jwt.expiration=3600000
spring.datasource.url=...
```

---

# 🌐 API Gateway

Responsibilities:

* Route requests to services
* Validate JWT
* Handle security
* Central entry point

---

# 🐳 Docker Setup

### Run all services:

```bash
docker-compose up --build
```

---

# 🧪 Running Locally

## Step 1: Start Config Server

```
http://localhost:8888
```

## Step 2: Start Eureka Server

```
http://localhost:8761
```

## Step 3: Start Services

* user-service
* api-gateway

---

# 📖 Swagger API Docs

```
http://localhost:8081/swagger-ui/index.html
```

---

# ⚠️ Common Issues & Fixes

### ❌ Table error (`column id does not exist`)

✔ Fix: Drop table or use `@Table(name="users")`

---

### ❌ Swagger 500 error

✔ Fix: Use compatible Spring Boot + SpringDoc versions

---

### ❌ Config Server not loading

✔ Ensure:

```
spring.config.import=optional:configserver:http://localhost:8888
```

---

# 🧠 Key Concepts Demonstrated

* Microservices Architecture
* Service Discovery
* API Gateway Pattern
* Centralized Configuration
* JWT Authentication
* Database per service
* Clean layered architecture

---

# 👨‍💻 Author

**Akhil Puri**

---