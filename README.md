# 🚀 Idempotent Gateway

*A Spring Boot engine for safe, duplicate-proof API execution*

---

## 🧠 Overview

The **Idempotent Gateway** ensures that repeated API requests with the same key are processed **exactly once** — preventing duplicate transactions and inconsistent state.

> Same request + same idempotency key → same response, no re-execution.

---

## 🔥 Current Capabilities

* ✅ Prevents duplicate API execution
* ⚡ Handles concurrent requests safely
* 🧠 Stores and replays successful responses
* 🧩 Clean interceptor-based architecture

---

## ⚙️ How It Works (Current Flow)

```
Client Request
   ↓
[ Interceptor - preHandle ]
   ↓
Check Idempotency Table
   ├── EXISTS + COMPLETED → Return stored response ❌
   ├── EXISTS + PROCESSING → Return 409 ❌
   └── NOT EXISTS → Insert (PROCESSING) ✅
   ↓
Controller
   ↓
BusinessService (actual logic)
   ↓
Save to Payment Table
   ↓
Return Response
   ↓
[ Interceptor - afterCompletion ]
   ↓
Update Idempotency Table → COMPLETED
```

---

## 🏗️ Architecture

```
Client
  ↓
Interceptor (Idempotency Layer)
  ↓
Controller
  ↓
BusinessService
  ↓
Database
```

---

## 🗃️ Database Design

### 🔹 Idempotency Table

```
idempotency_records
--------------------
id
idempotency_key (UNIQUE)
status (PROCESSING, COMPLETED)
response_body
created_at
```

---

### 🔹 Payment Table

```
payments
---------
id
transaction_id
amount
currency
status
```

---

## 📡 API Example

### Request

```
POST /payments
Headers:
  Idempotency-Key: abc123

Body:
{
  "amount": 5000,
  "currency": "INR"
}
```

---

### First Response

```json
{
  "status": "SUCCESS",
  "transactionId": "tx_12345"
}
```

---

### Duplicate Request (Same Key)

```json
{
  "status": "SUCCESS",
  "transactionId": "tx_12345"
}
```

---

## ⚡ Status Lifecycle (Current)

| Status     | Meaning                        |
| ---------- | ------------------------------ |
| PROCESSING | Request is currently executing |
| COMPLETED  | Request finished successfully  |

---

## ⚠️ Known Limitation

This version does **not yet handle failures or retries**.

### Edge Case:

```
Request A → marked PROCESSING
BusinessService fails ❌
```

Then:

```
Status remains PROCESSING indefinitely
```

Subsequent requests:

```
Same key → always returns 409 (blocked)
```

👉 This can lead to **stuck requests**

---

## 🧪 Testing Concurrency

To simulate real-world race conditions:

1. Add delay in interceptor:

   ```java
   Thread.sleep(3000);
   ```

2. Fire two requests with same key:

   * First → processes
   * Second → returns `409 Conflict`

---

## 🔧 Tech Stack

* Java + Spring Boot
* Spring MVC Interceptors
* Spring Data JPA
* MySQL / H2
* Maven

---

## 🚀 Future Improvements

* 🔁 Add `FAILED` state for failure handling
* 🔄 Retry mechanism for failed requests
* ⏳ Timeout for stuck PROCESSING states
* 🔥 Redis caching for faster lookups
* 🔒 Distributed locking
* 📊 Monitoring & metrics

---

## 💡 Key Insight

> Idempotency is about controlling execution, not assuming success.

---

## ✨ Author

**Utkarsh Shresth**
---

## ⭐ If you found this useful

Give it a ⭐ and build something even better 🚀
