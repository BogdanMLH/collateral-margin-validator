# Collateral Margin Validator 🏦

An event-driven Spring Boot microservice that consumes real-time portfolio updates via Kafka, validates collateral thresholds, and triggers Margin Call alerts — persisting all incidents to PostgreSQL for auditing.

> ⚠️ This is an MVP / proof-of-concept. Not production-ready.

---

## How It Works

1. A **producer** periodically emits simulated `PortfolioUpdate` events to a Kafka topic.
2. The **validator** consumes them and checks if `currentValue < requiredValue`.
3. On shortfall: publishes a `MarginCallAlert` to Kafka **and** persists the event to PostgreSQL.

---

## Tech Stack

`Java 17` · `Spring Boot 3.x` · `Apache Kafka (KRaft)` · `PostgreSQL` · `Docker Compose` · `Lombok` · `Jackson`

---

## Running Locally

```bash
# 1. Start Kafka + PostgreSQL
docker-compose up -d

# 2. Run the app
./mvnw spring-boot:run
```

That's it. The producer starts emitting events automatically. Watch the logs for `[MARGIN CALL TRIGGERED]`.

---
