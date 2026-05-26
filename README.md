# Collateral Margin Validator 🏦

An event-driven Spring Boot microservice designed to process real-time financial portfolio updates, validate collateral requirements, and generate Margin Call alerts using Apache Kafka and PostgreSQL.

## 📌 Project Overview
In global financial markets, institutions must maintain sufficient collateral to cover their trading risks. This project simulates a core component of a **Collateral Management System**. 

The service asynchronously consumes simulated streams of asset valuations, evaluates them against required thresholds, and triggers critical alerts (`Margin Calls`) if a portfolio's value drops below the acceptable risk limit. All critical incidents are persisted in a relational database for auditing purposes.

## 🏗 Architecture
The project follows an Event-Driven Architecture (EDA) to ensure loose coupling and high scalability.

1. **Portfolio Update Producer (Simulated External Feed):** Periodically generates and publishes `PortfolioUpdate` events (simulating market fluctuations) to a Kafka topic.
2. **Apache Kafka (Event Bus):** Acts as a highly available message broker decoupling the data ingestion from the processing logic.
3. **Collateral Validator (Consumer):** Listens to the incoming stream, applies business rules (comparing `currentValue` vs `requiredValue`), and identifies shortfalls.
4. **Margin Call Publisher & Storage:** Upon detecting a shortfall, the service concurrently:
    * Publishes a `MarginCallAlert` to a dedicated Kafka topic for downstream notifications.
    * Persists the `MarginCallEvent` to a PostgreSQL database for historical auditing.

## 🛠 Tech Stack
* **Language:** Java 17+
* **Framework:** Spring Boot 3.x
* **Messaging Bus:** Apache Kafka (KRaft mode)
* **Database:** PostgreSQL
* **ORM:** Spring Data JPA / Hibernate
* **Containerization:** Docker & Docker Compose
* **Other Tools:** Lombok, Jackson (with JSR-310 support)

## 🚀 Quick Start

### Prerequisites
* Docker Desktop installed and running
* Java 17 or higher
* Maven

### Running the Infrastructure
Start the Apache Kafka broker and PostgreSQL database using Docker Compose:

docker-compose up -d
Kafka will be available on localhost:9092 and PostgreSQL on localhost:5432.

Running the Application
You can run the application directly via Maven:

Bash
./mvnw spring-boot:run
Observing the System
Once the application starts, check the terminal logs. You will observe:

INFO logs indicating new portfolio updates being published and consumed.

WARN logs displaying ⚠️ MARGIN CALL TRIGGERED when an asset's value drops below the threshold.

INFO logs confirming that the incident has been successfully saved to the PostgreSQL database.

💼 Business Value & Key Learnings
Domain-Driven Design (DDD): Built using financial industry terminology (Collateral, Margin Call, Shortfall) to accurately reflect business processes.

Resilience: Configured Kafka consumer with earliest offset reset to ensure zero data loss of financial events in case of application downtime.

Modern Serialization: Implemented Jackson modules for robust handling of Java 8 LocalDateTime across the event stream.

Auditability: Integrated persistent storage (Spring Data JPA) to ensure critical financial alerts are never lost.
