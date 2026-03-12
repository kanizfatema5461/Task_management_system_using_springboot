# Task Management System

A RESTful Task Management system built using Spring Boot, designed to demonstrate clean backend architecture, CRUD operations, DTO usage, and database persistence using modern Java development practices.

## 🎯 Project Objectives

- Build a scalable REST API for task management.
- Practice Spring Boot architecture and REST principles.
- Implement clean data handling using DTO.
- Provide structured task status tracking.
- Enable easy API testing through Swagger UI.

## 🚀 Features

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
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───example
│   │   │           └───taskmanager
│   │   │               ├───controllers                             # REST controllers that handle incoming HTTP requests.
│   │   │               ├───dto                                     # Data Transfer Objects for request and response payloads.
│   │   │               ├───entities
│   │   │                   ├── TaskManagerEntity.java              # JPA entity classes representing database tables.
│   │   │                   └── Status.java                         # Enum ensures controlled status values.
│   │   │               ├───repositories                            # Data access layer interfaces using Spring Data JPA.
│   │   │               └───services                                # Business logic and service-layer implementations.
│   │   └───resources
│   └───test
│       └───java
│           └───com
│               └───example
│                   └───taskmanager
└───README.md
```

#### The status is stored as a STRING in the database.

## ⚙️ Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Lombok
- REST API
- PostgreSQL (or any relational database)
- Swagger/OpenAPI (for API testing)

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

### Task Controller

| Method | End Points         | Description     |
| ------ | ------------------ | --------------- |
| PUT    | /tasks/update/{id} | Update Task     |
| POST   | /tasks/addtask     | Add new Task    |
| DELETE | /tasks/{id}        | Delete Task     |
| GET    | tasks/All          | Get All Task    |
| GET    | /tasks/{id}        | Get Single Task |

## 📌 Future Improvements

- Authentication & Authorization (Spring Security)
- Logging
