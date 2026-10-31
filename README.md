# ParkWise

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-6db33f)](https://spring.io/projects/spring-boot)
![Java](https://img.shields.io/badge/Java-21-ed8b00)
![PostgreSQL](https://img.shields.io/badge/DB-PostgreSQL-336791)
![IoT](https://img.shields.io/badge/IoT-Arduino-00979d)

A **connected (IoT) parking** project: an application to find, reserve and pay for parking spaces, backed by a Spring Boot API and an Arduino-based sensing layer.

## Features

- **Users & cars**: register users and their vehicles.
- **Parking spaces**: manage spaces and their status (free / occupied…).
- **Reservations**: reserve a space, with its lifecycle.
- **Payments**: pay for a reservation, with payment modes and statuses.
- **Live image feed**: image upload and a WebSocket channel (`SocketConfig`, `ImageSocketController`) for the camera/sensing side.
- **Security**: Spring Security configuration.

Domain: `Users`, `Cars`, `Parking`, `Reservation`, `Payment`, with controllers, services and repositories per entity, and enums for parking status, payment mode and payment status.

## Tech stack

Spring Boot 3 · Java 21 · Spring Data JPA · Spring Security · Spring Validation · Spring WebSocket · Spring Cloud OpenFeign · PostgreSQL · Lombok. Hardware side in `docs/util/` (Arduino `.ino` + support).

## Run the backend

```bash
git clone https://github.com/GAUSS-TPAC/ParkWise.git && cd ParkWise
# set your PostgreSQL connection in src/main/resources/application.properties
./mvnw spring-boot:run        # http://localhost:8080
```

A simple web client is in [`frontend/`](frontend/).

## Documentation

- [`docs/PROJECT_CONTEXT.md`](docs/PROJECT_CONTEXT.md) — project context
- `docs/cahier de charge.pdf` — requirements (French)
- [`docs/to_do.md`](docs/to_do.md) — roadmap

## Related

- [smartParking](https://github.com/GAUSS-TPAC/smartParking) — a companion backend scaffold for the same domain.

## License

MIT.
