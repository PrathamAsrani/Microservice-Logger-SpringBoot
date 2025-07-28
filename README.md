
# Logger Service API 🚀

A Spring Boot-based asynchronous logging service that stores and retrieves logs using MongoDB. It supports filtering by time range and log level, and is designed for scalable integration into distributed systems.

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- MongoDB
- Spring Data MongoDB
- Async (via `@EnableAsync`)
- SLF4J + Custom Logger Wrapper (`AppLogger`)

---

## 📦 Features

- ✅ Create structured log entries (`INFO`, `ERROR`, `LOG`, etc.)
- ✅ Store logs in MongoDB
- ✅ Filter logs by time range and log level
- ✅ Asynchronous processing using `@Async`
- ✅ Uses `MongoTemplate` for dynamic queries

---

## 📁 Folder Structure

```
src/
│
├── controller/
│   └── Logger_Controller.java
├── model/
│   └── LogEntry.java
├── model/dtos/
│   ├── LogRequestDTO.java
│   ├── LogResponseDTO.java
│   └── LogFilterDTO.java
├── service/
│   └── LogService.java
├── repository/
│   └── LogRepo.java
├── common/
│   └── AppLogger.java
└── exception/
    └── LogCreationException.java
```

---

## 📡 API Endpoints

### 1. Create Log

```
POST /api/logs/create-log
```

**Request Body:**
```json
{
  "payload": {
    "message": "Something went wrong",
    "user": "admin"
  },
  "level": "ERROR"
}
```

**Response:**
```json
{
  "id": "68860cef45adfa240ca87b2b",
  "createdAt": "2025-07-28T13:55:07.48Z",
  "payload": {
    "message": "Something went wrong",
    "user": "admin"
  },
  "logLevel": "ERROR"
}
```

---

### 2. Get Filtered Logs

```
POST /api/logs/get-logs
```

**Request Body:**
```json
{
  "startDate": "2025-07-01",
  "endDate": "2025-07-28",
  "level": "ERROR"
}
```

**Response:**
```json
[
  {
    "id": "68860cef45adfa240ca87b2b",
    "createdAt": "2025-07-28T13:55:07.48Z",
    "payload": {
      "message": "Something went wrong",
      "user": "admin"
    },
    "logLevel": "ERROR"
  }
]
```

---

## ⚙️ Configuration

### application.properties (dev)
```properties
spring.application.name=Logger Service
server.port=8081
spring.data.mongodb.uri=mongodb://localhost:27017/spring_logger_service
```

For multiple environments, use:
- `application-dev.properties`
- `application-prod.properties`
  And activate via:
```bash
-Dspring.profiles.active=dev
```

---

## 🧪 Run the Project

```bash
./mvnw spring-boot:run
```

---

## ✅ To Do

- [ ] Add log pagination
- [ ] Add log export/download
- [ ] Add authentication layer
- [ ] Unit & integration tests

---

## 👨‍💻 Author

**Pratham Asrani**  
📧 prathamasrani.cs@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/pratham-asrani-9897b0225) | [GitHub](https://github.com/PrathamAsrani)

**Vedant Zope**  
📧 vedantzope9@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/vedant-zope73/) | [GitHub](https://github.com/vedantzope9)
