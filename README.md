# Spring Boot Blog Application

## Table of Contents
1. Introduction
2. Project Setup
3. Technologies Used
4. Application Architecture
5. Database Schema
6. REST API Endpoints
7. Request and Data Flow
8. Exception Handling
9. Authentication and Security
10. Conclusion

---

## 1. Introduction
The **Spring Boot Blog Application** is a RESTful API designed for managing blog posts and comments efficiently. It enables users to perform CRUD operations on blog posts and interact with the content.

## 2. Project Setup
- **Spring Boot** for application development
- **Spring Data JPA** for database interactions
- **ModelMapper** for DTO to Entity conversion
- **MySQL** as the relational database
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management

## 3. Technologies Used
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- MySQL Database
- ModelMapper
- Lombok
- Maven
- Spring Security
- JSON Web Token (JWT)

## 4. Application Architecture
The application follows a layered architecture:
- **Controller Layer:** Handles HTTP requests and responses.
- **Service Layer:** Implements business logic.
- **Repository Layer:** Communicates with the database.
- **DTO Layer:** Transfers data between layers.
- **Security Layer:** Handles authentication and authorization.

## 5. Database Schema
The database consists of the following main tables:

### Post Table
| Column     | Type           | Description |
|------------|--------------|-------------|
| id         | Long         | Primary Key |
| title      | String       | Post Title  |
| content    | String       | Post Content |

### Comment Table
| Column     | Type           | Description |
|------------|--------------|-------------|
| id         | Long         | Primary Key |
| blog_id    | Long         | Foreign Key referencing Blog |
| comment    | String       | Comment Content |

### User Table (For Authentication)
| Column     | Type           | Description |
|------------|--------------|---------------|
| userId     | String       | Primary Key  |
| name       | String       | Name         |
| email      | String       | Unique email |
| password   | String       | Encrypted password |

## 6. REST API Endpoints
### Post Management
- **Create Blog Post:** `POST /api/blogs`
- **Get Post by ID:** `GET /api/blogs/{id}`
- **Update Post:** `PUT /api/blogs/{id}`
- **Delete Post:** `DELETE /api/blogs/{id}`

### Comment Management
- **Add Comment to Post:** `POST /api/blogs/comment`

### Authentication
- **User Login:** `POST /auth/login`
- **Register User:** `POST /auth/create-user`

## 7. Request and Data Flow
1. **User sends HTTP request** (e.g., `POST /api/blogs` with JSON payload).
2. **Controller processes request**, calls Service layer.
3. **Service applies business logic** and interacts with Repository.
4. **Repository queries database** using Spring Data JPA.
5. **Response is sent back** to the client as JSON.
6. **For secured endpoints, JWT Token is required in the request header.**

## 8. Exception Handling
- **Global Exception Handler** using `@ControllerAdvice`.
- **Custom Exceptions** for handling validation and not-found cases.
- **Standardized error response format**.

## 9. Authentication and Security
### **JWT-Based Security Implementation**

#### **1. User Registration and Login**
- Users can register with `POST /auth/create-user`.
- Users log in using `POST /auth/login` to receive a JWT token.
- Token must be included in the **Authorization** header for protected routes.
- 
#### **2. Security Configuration**

#### **3. Generating JWT Token**

#### **4. JWT Token Validation**

#### **5. Protecting API Endpoints**
- **Public endpoints**: Login and Register (`/auth/login`, `/auth/create-user`).
- **Protected endpoints**: Blogs, Comments (`/api/blogs/**` requires JWT).
- **Authorization header format**:
  ```
  Authorization: Bearer <JWT_TOKEN>
  ```

## 10. Conclusion
This project provides a structured and secure approach to blog management using Spring Boot. It ensures modularity, security, and scalability while offering efficient request handling. By implementing JWT authentication, the application protects sensitive endpoints while allowing authorized users to access resources securely.

