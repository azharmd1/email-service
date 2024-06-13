# Email Service

## Overview

The `email-service` is a Java-based microservice designed to listen to Kafka topics for email messages, process these messages, and send emails using SMTP. The service is built using Spring Boot and leverages Kafka for message consumption.

## Project Structure

The project is organized into the following packages:

- **consumers**: Contains classes responsible for consuming messages from Kafka topics.
- **dtos**: Contains Data Transfer Objects (DTOs) used within the application.
- **services**: Contains service classes providing functionalities such as sending emails.

### Directory Structure

```
src/main/java/com/emailservice
├── consumers
│   └── SendEmailConsumer.java
├── dtos
│   └── SendEmailMessageDto.java
└── services
    └── EmailUtil.java
```

## Packages and Classes

### Consumers Package

#### SendEmailConsumer

This class is responsible for listening to the Kafka topic `sendEmail` and processing incoming messages to send emails. It uses the `ObjectMapper` to convert messages into `SendEmailMessageDto` objects and sends emails using SMTP settings.

### Services Package

#### EmailUtil

This utility class provides methods for sending emails using the JavaMail API. It sets up email properties and sends the email to the specified recipient.

## Configuration

To configure the email service, set the following environment variables:

- `EMAIL_USERNAME`: Your email account username.
- `EMAIL_PASSWORD`: Your email account password.

## Running the Service

1. Ensure Kafka and Zookeeper are running on your machine.
2. Set the environment variables for the email username and password.
3. Build and run the Spring Boot application.

## Dependencies

The project includes the following dependencies:

- **Spring Boot Starter**: Provides core Spring framework support.
- **Spring Kafka**: Provides support for handling Kafka messages.
- **Spring Boot DevTools**: Provides development-time features to aid in application development.
- **Spring Boot Configuration Processor**: Generates metadata for configuration properties.
- **Lombok**: Reduces boilerplate code with annotations.
- **Spring Boot Starter Test**: Provides testing support for Spring Boot applications.
- **Spring Kafka Test**: Provides testing support for Kafka.
- **Spring Boot Starter Web**: Provides web application support.
- **JavaMail API**: Provides email sending support.


---

For any issues or contributions, please open an issue or submit a pull request to the repository.
