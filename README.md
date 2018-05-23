# TaskSkt

[![Build Status](https://travis-ci.com/Joxebus/TaskSkt.svg?branch=master)](https://travis-ci.com/Joxebus/TaskSkt)

Create a Spring Boot application which sends or receives JSON messages through RabbitMQ or Kafka according to the Spring profile active at runtime.

### Modules
**Management App:**
- 2 JSP one for create the products and other to list them.
- Controller to handle the requests.
- Service to send the message through a queue (using 2) to the microservice (3).

**Common Library:**
- Contains the POJO that will be used to serialize and deserialize between 1 and 2.
- Serializer and deserializer if required.

**Microservice:**
- Connects to the DB to save and get the data
- Consume the message from queue (using 2)
- Send the list of products that will be showed in the view list on 1  to a queue (using 2)
- The queue or topic depending on if is using RabbitMQ or Kafka
- The DB that will save the data (can be any)
- The insert and select will be through a Stored Procedure

### Requirements

- SpringBoot 1.5.7-RELEASE 
- JDK 1.8
- Maven 3
- RabbitMQ or Kafka
- Docker for all DB’s and Brokers
- JUnit

### Expectations

- Send and receive a POJO in JSON format.
- Listen to messages in RabbitMQ  or Kafka using a non-default port.
- Use a Spring Cloud Config server to externalize the application settings, like RabbitMQ  and Kafka port, to a local Git repository (nice to have)
- Use docker compose to get rabbit and kafka running (nice to have).
- 80% test coverage is expected on unit tests

