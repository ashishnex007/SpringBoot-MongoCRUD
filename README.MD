## JOURNAL APP

### Learning Spring Boot

### Technologies
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- Redis
- MongoDB
- Java Mail Sender - SMTP

### Implementing Logic Throughout the Spring Boot Project
**Services -> Repositories -> Controllers**

1. **Controllers (Presentation Layer)**
   - **Purpose:** Handle HTTP requests and return responses.
   - **Responsibilities:**
     - Receive input from users via endpoints (e.g., REST APIs).
     - Validate input (optional if delegated to services).
     - Call the service layer to execute business logic.

2. **Services (Business Logic Layer)**
   - **Purpose:** Encapsulate business logic and coordinate between controllers and repositories.
   - **Responsibilities:**
     - Implement core business logic.
     - Handle validations and error handling.
     - Interact with one or more repositories.

3. **Repositories (Data Access Layer)**
   - **Purpose:** Communicate with the database.
   - **Responsibilities:**
     - Define methods for data retrieval, insertion, updates, and deletions.
     - Leverage Spring Data JPA or any ORM framework.

4. **Entities (Data Model)**
   - **Purpose:** Represent database tables as Java objects.
   - **Responsibilities:**
     - Define fields and relationships.
     - Annotate with JPA annotations to map to database tables.

## Milestones

### Adding JavaMailSender
- Add JavaMailSender dependency in `pom.xml`.
- Create a Gmail account, enable 2FA, and generate an app password.
- Add JavaMailSender configuration in `application.yml` and include the email and password.
- Create a service to send emails (`EmailService.java`) with a `sendEmail` method.

### Adding Schedulers Using `@Scheduled`
- Create a scheduler class (`Scheduler`).
- Add `@EnableScheduling` annotation in the main class.
- Add `@Scheduled` annotation to methods to run them at specific times using CRON expressions.

### Connecting with Redis
- Install Redis.
- Add Redis dependency in `pom.xml`.
- Add Redis configuration in `application.yml` and `RedisConfig.java` to connect with the Redis server using `RedisTemplate`.
- Create a service (`RedisService.java`) to save and retrieve data from Redis with `get` and `set` methods.
- Create a controller (`RedisController.java`) to interact with Redis.

### Connecting with Redis Cloud
- Create an account in Redis Cloud.
- Create a Redis Cloud instance.
- Add Redis Cloud configuration in `application.yml` and `RedisCloudConfig.java` to connect with the Redis Cloud server.
- Use similar service and controller to interact with Redis Cloud.