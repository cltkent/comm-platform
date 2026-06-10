# Communication Services Platform

Spring Boot 3.3.5 communication platform for email, LINE, Telegram, WebSocket, SSE, Firebase push notifications, TOS integration, and multi-broker event distribution.

## English

### Tech stack
- Java 21
- Spring Boot 3.3.5
- Gradle Kotlin DSL
- Web, WebSocket, WebFlux, Mail, JPA, Actuator, Thymeleaf
- Kafka, RabbitMQ, Redis
- H2 (dev), PostgreSQL (prod)

### Features
- Email sending via SMTP/Thymeleaf templates
- Message dispatching for LINE and Telegram
- Realtime delivery with WebSocket/STOMP and SSE
- Push notifications with Firebase Cloud Messaging
- Internal and broker-backed event publishing
- TOS inbound integration endpoint
- Admin APIs for broadcast and event history
- Swagger UI and Actuator support

### Project structure
```text
com.clt.toscana
├── config/
├── controller/
├── service/
│   ├── email/
│   ├── messaging/
│   ├── realtime/
│   ├── notification/
│   └── event/
├── broker/
│   ├── kafka/
│   ├── rabbitmq/
│   └── redis/
├── tos/
├── history/
├── admin/
└── model/
```

### Prerequisites
- JDK 21
- Gradle 8+ (or use `./gradlew` after wrapper generation)
- Docker / Docker Compose (optional)

### Run locally
```bash
gradle wrapper --gradle-version 8.10.2
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### Build
```bash
./gradlew clean build
```

### API docs
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`
- Actuator: `http://localhost:8080/actuator/health`

### Docker
```bash
docker compose up --build
```

### Main endpoints
- `POST /api/v1/email/send`
- `POST /api/v1/message/send`
- `GET /api/v1/sse/subscribe/{clientId}`
- `POST /api/v1/notification/push`
- `POST /api/v1/events/publish`
- `POST /api/v1/tos/inbound`
- `GET /api/v1/admin/history`
- `POST /api/v1/admin/broadcast`
- `POST /api/v1/admin/sse/broadcast`

## Tiếng Việt

### Tổng quan
Đây là nền tảng dịch vụ truyền thông xây dựng bằng Spring Boot 3.3.5, hỗ trợ email, LINE, Telegram, WebSocket, SSE, Firebase, tích hợp TOS và phân phối sự kiện qua nhiều broker.

### Công nghệ sử dụng
- Java 21
- Spring Boot 3.3.5
- Gradle Kotlin DSL
- Web, WebSocket, WebFlux, Mail, JPA, Actuator, Thymeleaf
- Kafka, RabbitMQ, Redis
- H2 cho môi trường dev, PostgreSQL cho môi trường prod

### Chức năng chính
- Gửi email qua SMTP hoặc template Thymeleaf
- Gửi tin nhắn qua LINE và Telegram
- Giao tiếp realtime bằng WebSocket/STOMP và SSE
- Gửi push notification qua Firebase Cloud Messaging
- Publish event nội bộ và đẩy ra broker đang active
- Nhận sự kiện inbound từ hệ thống TOS
- API quản trị để broadcast và xem lịch sử event
- Tài liệu API bằng Swagger/OpenAPI

### Cách chạy local
```bash
gradle wrapper --gradle-version 8.10.2
./gradlew bootRun --args='--spring.profiles.active=dev'
```

### Build project
```bash
./gradlew clean build
```

### Chạy bằng Docker Compose
```bash
docker compose up --build
```

### Cấu hình quan trọng
Các biến môi trường chính:
- `BROKER_ACTIVE`
- `KAFKA_BOOTSTRAP_SERVERS`
- `RABBITMQ_HOST`
- `REDIS_HOST`
- `DB_HOST`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`
- `MAIL_HOST`, `MAIL_USERNAME`, `MAIL_PASSWORD`
- `TOS_BASE_URL`, `TOS_API_KEY`
- `LINE_CHANNEL_TOKEN`
- `TELEGRAM_BOT_TOKEN`, `TELEGRAM_BOT_USERNAME`

### Ghi chú
- File `firebase-service-account.json` không được commit vào repository.
- Profile `dev` dùng H2 in-memory để phát triển nhanh.
- Profile `prod` dùng PostgreSQL và kiểm tra schema khi khởi động.
