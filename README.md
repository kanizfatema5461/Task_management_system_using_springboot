# Task Management System

A RESTful Task Management system built using Spring Boot, designed to demonstrate clean backend architecture, CRUD operations, DTO usage, and database persistence using modern Java development practices.

## 🎯 Project Objectives

- Build a scalable REST API for task management.
- Practice Spring Boot architecture and REST principles.
- Implement clean data handling using DTO.
- Provide structured task status tracking.
- Enable easy API testing through Swagger UI.

## 🚀 Features

### User Authentication

- Login with JWT Token
- User Registration
- User Login with JWT Authentication
- Secure API endpoints using Spring Security
- Secure Password Encryption

### Email Verification

- After registration, an OTP is sent to the user's email
- User must verify the OTP to activate the account

### Password Management

- Forgot Password
- Reset Password using OTP verification

### Task Management (CURD)

- Create Task (Admin & User)
- View Task
- Update Task
- Delete Task (Only admin)

- **Full CRUD Operations:** Manage tasks efficiently.
- **Data Transfer Objects (DTO):** Secure data handling using Java Records for request payloads.
- **Status Management:** Tasks are categorized into Pending, In_progress, and Complete using Enums.
- **Database Persistence:** Powered by Spring Data JPA and Hibernate.
- **API Documentation:** Integrated Swagger/OpenAPI UI for easy testing.

## 🧱 Project Architecture

The project follows a standard layered Spring Boot architecture:

```
Client (Browser / Mobile / API Tool)
                 ↓
          Controller Layer
      (REST Controllers / MVC)
                 ↓
           Service Layer
          (Business Logic)
                 ↓
          Repository Layer
               (JPA)
                 ↓
             Database
```

## 📂 Project Structure

```
taskmanager
├───.mvn
│   └───wrapper
│
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           └───taskmanager
│   │   │               │
│   │   │               ├───config
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   ├── SecurityConfiguration.java
│   │   │               │   └── SwaggerConfig.java
│   │   │               │
│   │   │               ├───controller                     # Handles HTTP requests
│   │   │               │   ├── AdminController.java
│   │   │               │   ├── AuthenticationController.java
│   │   │               │   ├── TaskManagerController.java
│   │   │               │   └── UserController.java
│   │   │               │
│   │   │               ├───dto                            # Request & Response models
│   │   │               │   ├── ForgotPasswordRequest.java
│   │   │               │   ├── JwtAuthenticationResponse.java
│   │   │               │   ├── RefreshTokenRequest.java
│   │   │               │   ├── ResetPasswordRequest.java
│   │   │               │   ├── SigninRequest.java
│   │   │               │   ├── SignupRequest.java
│   │   │               │   ├── SignupResponse.java
│   │   │               │   ├── TaskReqDto.java
│   │   │               │   └── VerifyOtpRequest.java
│   │   │               │
│   │   │               ├───entity                         # Database tables (JPA)
│   │   │               │   ├── Role.java
│   │   │               │   ├── Status.java
│   │   │               │   ├── TaskManagerEntity.java
│   │   │               │   └── User.java
│   │   │               │
│   │   │               ├───repository                     # Data access layer
│   │   │               │   ├── TaskManagerRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               │
│   │   │               ├───service                        # Interfaces
│   │   │               │   ├── AuthenticationService.java
│   │   │               │   ├── EmailService.java
│   │   │               │   ├── JwtService.java
│   │   │               │   ├── TaskManagerService.java
│   │   │               │   └── UserService.java
│   │   │               │
│   │   │               │   └───impl                       # Implementations
│   │   │               │       ├── AuthenticationServiceImpl.java
│   │   │               │       ├── EmailServiceImpl.java
│   │   │               │       ├── JWTServiceImpl.java
│   │   │               │       └── UserServiceImpl.java
│   │   │               │
│   │   │               ├───security                       # (currently empty / future use)
│   │   │               │
│   │   │               ├───util
│   │   │               │   └── OtpUtil.java
│   │   │               │
│   │   │               └── TaskmanagerApplication.java    # Main class
│   │   │
│   │   └───resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   │
│   └───test
│       └───java
│           └───com
│               └───example
│                   └───taskmanager
│
└───README.md
```

## ⚙️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- JWT (JSON Wbe Token)
- Hibernate
- Lombok
- REST API
- PostgreSQL (or any relational database)
- Swagger/OpenAPI (for API testing)
- maven

## 🛠 Prerequisites

To run this project, you need:

- Java (JDK 17 or later)
- Maven
- PostgreSQL
- Any IDE (IntelliJ IDEA, Eclipse, or VS Code)

#### Basic Knowledge Required

- Java
- Spring Boot
- REST API JPA / Hibernate

> ⚠️ **Make sure PostgreSQL is running and database credentials are set correctly in `application.properties`.**

## ▶️ How to Run

```
git clone https://github.com/kanizfatema5461/Task_management_system_using_springboot
mvn spring-boot:run

or run the main class from IDE.
```

## 🌐 Access Swagger UI

http://localhost:8080/swagger-ui/index.html

## 🧩 API Endpoints

### Authentication APIs

| Method | End Points       | Description                 |
| ------ | ---------------- | --------------------------- |
| POST   | /signup          | Register new user           |
| POST   | /verify-email    | verify email                |
| POST   | /signin          | Login User                  |
| POST   | /forgot-password | Send OTP for password reset |
| POST   | /reset-password  | Reset Password              |

### Task Controller

| Method | End Points         | Description     |
| ------ | ------------------ | --------------- |
| PUT    | /tasks/update/{id} | Update Task     |
| POST   | /tasks/addtask     | Add new Task    |
| DELETE | /tasks/{id}        | Delete Task     |
| GET    | tasks/All          | Get All Task    |
| GET    | /tasks/{id}        | Get Single Task |

## Security Implementation

This project uses Spring Security with JWT authentication.

#### Security features include:

- Password encryption using BCryptPasswordEncoder
- JWT token generation after successful login
- Authorization using Bearer Token
- Protected endpoints requiring authentication
