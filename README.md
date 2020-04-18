# Communication Delivery Platform.

This repository contains a proposed architecture document and first iteration of implementation for a direct to consumer communication delivery platform.

- [Problem Statement](docs/problem-statement.md)
- [Proposed Design](docs/design.md)
- [Implementation Notes](docs/implementation-notes.md)

### How to Run

#### Prerequisites
- Java 11
- ActiveMQ

#### Steps to Run
- Build
	- `./mvnw clean install`
- Run
	- `./mvnw spring-boot:run`

#### Endpoints
The Server will run on `localhost:8080`. A Swagger API Documentation page can be found at:

- `http://localhost:8080/swagger-ui.html`

