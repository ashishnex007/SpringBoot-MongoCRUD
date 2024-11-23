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
- JWT
- Swagger

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

---
## Setting Up Spring Boot Project
1. **Create a Spring Boot Project** using [Spring Initializr](https://start.spring.io/).
2. **Add Dependencies** in `pom.xml`: whatever you need. 
3. **Create Entities** to represent database tables.
4. **Create Repositories** to interact with the database.
5. **Create Services** to implement business logic.
6. **Create Controllers** to handle HTTP requests.
7. **Run the Application** and test the endpoints.
8. **Add Swagger** for API documentation.
9. **Add Spring Security** for authentication and authorization.
10. **Add JWT** for token-based authentication.
11. **Just keep running the fucking application**
---

## Milestones throughout this project

### Creating a Simple REST API
- Create a simple REST API with CRUD operations.
- Use `@RestController` and `@RequestMapping` annotations.
- Use `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` annotations.
- Use `@PathVariable` and `@RequestBody` annotations to handle path variables and request bodies.
- Use `@ResponseStatus` annotation to set the HTTP status code.
- Use `@Valid` and `@RequestBody` annotations for request body validation.
- Use `@Repository`, `@Service`, and `@Autowired` annotations for repository and service classes.

### Connecting with MongoDB Compass
- Install MongoDB Compass and setup that.
- Add the local URL to application.properties
- Add MongoInterface and MongoRepository

### Add Lombok
- Add Lombok dependency in pom.xml
- Use Lombok Annotations like `@Getter` and `@Setter`
- `@NoArgsConstructor`, `@AllArgsConstructor`, and `@RequiredArgsConstructor` for generating constructors.
```java
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.AllArgsConstructor;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class User {
        private String id;
        private String userName;
        private String email;
    }
```

### Maintaining Atomicity by using `@Transactional`
- Use `@Transactional` annotation to maintain atomicity in database operations.
- If an exception occurs, the transaction will be rolled back to the previous state.

### Adding Spring Security
- Add Spring Security dependency in `pom.xml`.
- Create a security configuration class (`SecurityConfig.java`) to configure security settings.
- Use `@EnableWebSecurity` and `@Configuration` annotations.
- Override `configure` method to set user details and password encoder.
- Use `BCryptPasswordEncoder` to encode passwords.
- Create a service (`UserDetailsService.java`) to load user details from the database.
- Use `@PreAuthorize` annotation to restrict access to specific endpoints.
- Use `@Secured` annotation to restrict access based on roles.
- Use `@EnableGlobalMethodSecurity` annotation to enable method-level security.

### Added Profiles
- Create `application-dev.yml` and `application-prod.yml` files for different profiles.

### Added Logging with SLF4J
- Add SLF4J dependency in `pom.xml`.
- Add `logbaack.xml` in resources folder and configure log levels.
- Use `LoggerFactory` to create a logger instance.
- Use `info`, `debug`, `warn`, and `error` methods to log messages.
- Use placeholders `{}` to pass arguments to log messages.
- Use `@Slf4j` annotation to generate a logger instance.

### Integrating External APIs
- Use `RestTemplate` to make HTTP requests to external APIs.

### .env File
- Create a `.env` file to store environment variables.
- Add the .env file to `.gitignore` to prevent it from being pushed to the repository.
- Add `.env` file to InntelliJ IDEA's Project Configurations so that the variables will be detected.
- Use ${} syntax to access environment variables in `application.yml`.
- Use `@Value` annotation to inject environment variables in Spring components.

### addCriteria in MongoDB
- Use `Criteria` and `Query` classes to create complex queries in MongoDB.

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

---

## Learning Apache Kafka

Apache Kafka is a distributed event-streaming platform capable of handling trillions of events per day. It provides high-throughput, fault-tolerant, and scalable systems to manage data streams in real-time.

---

### Key Concepts

### Kafka Cluster
A **Kafka Cluster** is a collection of Kafka brokers that work together to provide a distributed system for managing data streams.

### Kafka Broker
A **Kafka Broker** is a Kafka server that stores data and serves client requests (producers and consumers).

### Kafka Topic
A **Kafka Topic** is a category or feed name to which records are sent by producers. Topics organize the data streams.

- **Partitions**: A topic is divided into multiple partitions to allow data parallelism.
- **Offset**: Each record in a partition has a unique ID called the offset, which helps in identifying and accessing records.

### Kafka Producer
A **Kafka Producer** is a client application responsible for sending records to a Kafka topic.

- **Partitioner**: A class in the producer that determines which partition a record should be sent to.

### Kafka Consumer
A **Kafka Consumer** is a client application responsible for reading records from a Kafka topic.

### Kafka Streams
**Kafka Streams** is a client library for building real-time, scalable, and fault-tolerant applications and microservices. It enables stream processing directly within a Kafka ecosystem.

### Kafka Connect
**Kafka Connect** is a declarative integration framework designed to stream data between Kafka and other systems.

- **No Code Integration**: Kafka Connect allows you to configure source and destination systems without writing custom code. Data is directly fetched from the source and written to the destination.

### Why Didn't Integrate Cloud Kafka?
Cuz it's paid and I'm broke. 😅

### Running Kafka Locally

1. **Download Kafka**: Download the Kafka binaries from the [official website](https://kafka.apache.org/downloads).
2. **Extract Kafka**: Extract the downloaded Kafka binaries to a folder.
3. **Start Zookeeper**: Go the windows folder in the Kafka directory and run the following command to start Zookeeper:
   ```bash
   .\zookeeper-server-start.bat ..\..\config\zookeeper.properties
   ```
4. **Start Kafka Server**: Go the windows folder in the Kafka directory and run the following command to start the Kafka server:
   ```bash
    .\kafka-server-start.bat ..\..\config\server.properties
    ```
5. **Create a Topic**: Run the following command to create a Kafka topic:
    ```bash
    .\kafka-topics.bat --create --topic my-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3
    ```
6. **Produce Messages**: Run the following command to start a Kafka producer:
    ```bash
    .\kafka-console-producer.bat --broker-list localhost:9092 --topic my-topic
    ```
7. **Consume Messages**: Run the following command to start a Kafka consumer:
    ```bash
    .\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic my-topic --from-beginning
    ```
   
---

### JWT (JSON Web Token)
JWT contains three parts:
- **Header**: Contains the type of token (JWT) and the signing algorithm.
- **Payload**: Contains the claims, which are statements about an entity (typically, the user) and additional data.
- **Signature**: Used to verify the message wasn't changed along the way and, in the case of tokens signed with a private key, to verify that the sender of the JWT is who it says it is.

JWT is used to authenticate users and authorize access to resources.

### Integrating Swagger in Spring Boot
Swagger is a tool that helps in documenting and testing RESTful APIs. It provides a user-friendly interface to interact with the APIs and understand their functionality.

Steps to integrate Swagger in a Spring Boot application:
1. **Install Swagger dependencies** in `pom.xml`:
   ```xml
   <dependency>
       <groupId>org.springdoc</groupId>
       <artifactId>springdoc-openapi-ui</artifactId>
       <version>1.6.14</version>
   </dependency>
   ```
2. **Add Swagger configuration** in `SwaggerConfig.java`:
   ```java
   package net.engineeringdigest.journalApp.config;

   import io.swagger.v3.oas.models.OpenAPI;
   import io.swagger.v3.oas.models.info.Info;
   import org.springframework.context.annotation.Bean;
   import org.springframework.context.annotation.Configuration;

   @Configuration
   public class SwaggerConfig {

       @Bean
       public OpenAPI customOpenAPI() {
           return new OpenAPI().info(
               new Info().title("Journal App API")
                         .description("By Ash")
           );
       }
   }
   ```
3. **Annotate controllers** with Swagger annotations to provide additional information about the APIs.
4. **Access the Swagger UI** at `http://localhost:8080/api/swagger-ui/index.html#/` or `http://localhost:8080/api/docs` (the configured path).

---
I hope this Shit will help me in the future or anyone who sees this :) Sayonara, Attack Until Ashes!