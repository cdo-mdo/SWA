# MicroBank Saga (Checking + Saving + Transfer)

This sample shows **two microservices** (Checking & Saving) and a **client/orchestrator service** (Transfer)
that moves money from a checking account to a saving account using a **Saga with compensation**.
It demonstrates how to get ACID-like behavior across services by using **idempotent operations** and
**compensating actions** instead of a distributed 2PC transaction.

## Tech
- Java 21
- Spring Boot 3.3.x
- H2 in-memory DBs
- RestTemplate HTTP calls (no Spring Cloud required)

## Ports
- Checking: `8081`
- Saving:   `8082`
- Transfer: `8080`

## Getting Started (IntelliJ / Maven)
1. Import the root `pom.xml` as a Maven project.
2. Run `CheckingAccountServiceApplication`, `SavingAccountServiceApplication`, and `TransferServiceApplication`.
3. Use the following **curl script** to test.

### Create accounts
```bash
# Create checking account with $100
curl -s -X POST localhost:8081/accounts -H "Content-Type: application/json"   -d '{"owner":"Alice","initial":100}'

# Create saving account with $10
curl -s -X POST localhost:8082/accounts -H "Content-Type: application/json"   -d '{"owner":"Alice","initial":10}'
```
Note the `id` values printed for each created account (checkingId, savingId). In a fresh run they will both be `1` per service.

### Happy path transfer
```bash
# Transfer $25 from checking(1) -> saving(1)
curl -s -X POST localhost:8080/transfers -H "Content-Type: application/json"   -d '{"fromCheckingId":1,"toSavingId":1,"amount":25,"simulateSavingError":false}'
```

### Verify balances
```bash
# Query current balances
curl -s localhost:8081/accounts/1 | jq
curl -s localhost:8082/accounts/1 | jq

# H2 consoles (optional):
# Checking DB: http://localhost:8081/h2-console
# Saving   DB: http://localhost:8082/h2-console
# (Use jdbc URLs from application.yml.)
```

### Simulate an error to test compensation
The saving service can **simulate a failure** during deposit. The transfer service
will then **compensate** by refunding the withdrawal on the checking service.

```bash
# Simulate failure at Saving.deposit
curl -s -X POST localhost:8080/transfers -H "Content-Type: application/json"   -d '{"fromCheckingId":1,"toSavingId":1,"amount":15,"simulateSavingError":true}'
# Expect HTTP 500 from transfer-service. Now checking balance should be refunded.
```

### How it works
- `transfer-service` calls:
  1) `checking.withdraw(transferId, accountId, amount)`
  2) `saving.deposit(transferId, accountId, amount)`
- If step 2 fails, it calls `checking.compensate(transferId)` to refund.
- **Idempotency**: Each service records a `LedgerEntry` using `transferId` + operation type to avoid duplicate processing.
- **Optimistic locking** via `@Version` on Account prevents race conditions.

### Notes
- Doing a **single ACID transaction across two independent services/databases** is generally not recommended
  and is **not implemented** here. Instead, we use a small **Saga** to keep services independent.
- If you *must* do distributed XA/2PC, look at Narayana/Atomikos and shared transaction managers
  but be aware of operational complexity.

## Clean shutdown
Stop the three apps in any order; H2 is in-memory so data resets next run.
