Task Management System

A robust RESTful API built with Spring Boot to manage daily tasks. This application allows users to Create, Read, Update, and Delete (CRUD) tasks, featuring status tracking and database persistence.

🚀 Features
✅Full CRUD Operations: Manage tasks efficiently.
✅Data Transfer Objects (DTO): Secure data handling using Java Records for request payloads.
✅Status Management: Tasks are categorized into Pending, In_progress, and Complete using Enums.
✅Database Persistence: Powered by Spring Data JPA and Hibernate.
✅API Documentation: Integrated Swagger/OpenAPI UI for easy testing.

🧱 Project Architecture

The project follows a standard layered Spring Boot architecture:

Controller Layer
       ↓
Service Layer
       ↓
Repository Layer (JPA)
       ↓
Database

📂 Project Structure

taskmanager  
├───.mvn  
│   └───wrapper  
├───src  
│   ├───main  
│   │   ├───java  
│   │   │   └───com  
│   │   │       └───example  
│   │   │           └───task_management_system  
│   │   │               ├───controllers                            # REST controllers that handle incoming HTTP requests.
│   │   │               ├───dto                                    # Data Transfer Objects for request and response payloads.
│   │   │               ├───entities               
│   │   │                    ├── TaskManagerEntity.java            # JPA entity classes representing database tables.
│   │   │                    └── Status.java                       # Enum ensures controlled status values.
│   │   │               ├───exceptions                             # Custom exception classes for application-specific errors.
│   │   │               ├───repositories                           # Data access layer interfaces using Spring Data JPA.
│   │   │               └───services                               # Business logic and service-layer implementations.
│   │   └───resources  
│   └───test  
│       └───java  
│           └───com  
│               └───example  
│                   └───taskmanager  
└───README.md

The status is stored as a STRING in the database.

⚙️ Technologies Used

➜Java
➜Spring Boot
➜Spring Web
➜Spring Data JPA
➜Hibernate
➜Lombok
➜REST API
➜PostgreSQL (or any relational database)
➜Swagger/OpenAPI (for API testing)

🛠 Prerequisites

To run this project, you need:

Java (JDK 17 or later)
Maven
PostgreSQL
Any IDE (IntelliJ IDEA, Eclipse, or VS Code)

Basic Knowledge Required

Java
Spring Boot
REST API
JPA / Hibernate
Make sure PostgreSQL is running and database credentials are set correctly in application.properties.


▶️ How to Run the 
git clone https://github.com/kanizfatema5461/Task_management_system_using_springboot
mvn spring-boot:run
or run the main class from IDE.

5️⃣ Access Swagger UI
http://localhost:8080/swagger-ui/index.html

📌 Future Improvements

✅Authentication & Authorization (Spring Security)
✅Logging
