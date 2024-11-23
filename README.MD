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

## Milestones throughout this project

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

## Key Concepts

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


