
---

# Task Management System - Backend

This project is a task management system backend built using the Spring Boot framework and microservices architecture. It provides various functionalities for managing tasks, users, and task submissions.

## Dependencies

- **Spring Web**: Used for building RESTful APIs and web applications.
- **Spring Data JPA**: Provides easy integration with databases and simplifies data access.
- **MySQL Driver**: JDBC driver for connecting to MySQL database.
- **Lombok**: Library for reducing boilerplate code in Java classes.
- **Spring Cloud LoadBalancer**: Enables client-side load balancing for microservices.
- **Spring Security**: Provides security features for protecting endpoints and controlling access.
- **Spring Boot DevTools**: Tools for improving development experience, including automatic restarts.
- **Eureka Discovery Client**: Integrates microservices with the Eureka server for service discovery.
- **OpenFeign**: Declarative REST client for making API calls to other microservices.
- **Eureka Server**: Service registry and discovery server for managing microservices.
- **Gateway**: API gateway for routing requests to appropriate microservices.

## Microservices

- **Eureka Server**: Port 8070
- **Gateway**: Port 5000
- **Task User Service**: Port 5001
- **Task Service**: Port 5002
- **Task Submission Service**: Port 5003

## Getting Started

1. Start the Eureka Server by running the `EurekaServerApplication`.
2. Start the Gateway service by running the `GatewayApplication`.
3. Start the remaining microservices (`TaskUserService`, `TaskService`, `TaskSubmissionService`) individually.

## Usage

1. Use the API endpoints provided by the microservices to manage tasks, users, and task submissions.
2. Ensure proper authentication and authorization using Spring Security features.
3. Utilize Eureka server for service registration and discovery.
4. Configure the API gateway for routing requests and applying cross-cutting concerns such as logging and security.

## Contributing

Contributions to this project are welcome! Feel free to open issues for bug fixes or feature requests, and submit pull requests to contribute improvements.

## License

This project is licensed under the [MIT License](LICENSE).

---
