# Gemini Context: vg-code

## Project Overview
`vg-code` is a Java-based backend application built with **Spring Boot 4.0.4** and **Java 21**. It appears to be a management system (possibly for manufacturing or inventory, given entities like `Bobbin`, `PalletTable`, `Wire`, etc.).

### Core Technologies
- **Framework:** Spring Boot 4.0.4
- **Language:** Java 21
- **Database:** PostgreSQL
- **Database Migrations:** Liquibase
- **Persistence:** Spring Data JPA (Jakarta Persistence)
- **Containerization:** Podman (for local development database)
- **Build Tool:** Maven

## Project Structure
The project is organized as a multi-module Maven project (though currently only `pv-app` is present as a module).

- `pv-app/`: The main Spring Boot application module.
  - `src/main/java/org/vg/pv/app/`: Core source code.
    - `controllers/`: REST API endpoints.
    - `entities/`: JPA entities.
    - `repositories/`: Spring Data JPA repositories.
    - `services/`: Business logic layer.
  - `src/main/resources/`:
    - `db/changelog/`: Liquibase migration files.
    - `podman-files/`: Podman configuration for local environment.

## Building and Running

### Prerequisites
- Java 21 JDK
- Podman (or Docker) for local database

### Infrastructure Setup
To start the local PostgreSQL database:
```powershell
cd pv-app/src/main/resources/podman-files/
podman-compose up -d
```

### Build the Project
From the root directory:
```powershell
./mvnw clean install
```

### Run the Application
From the `pv-app` directory:
```powershell
./mvnw spring-boot:run
```

## Development Conventions

### Coding Style
- **Dependency Injection:** Prefer constructor injection over field injection (e.g., `@Autowired` on constructors).
- **Lombok Usage:** The project **does not** use Lombok. Use manual getters, setters, and constructors.
- **Entities:** Uses standard Jakarta Persistence annotations. Entities include `createdAt` and `updatedAt` timestamps (managed manually in services).
- **API Design:** RESTful controllers returning `ResponseEntity`.
- **Database:** 
  - Schema is defined in `application.properties` as `postgres-vg-sc1`.
  - Hibernate DDL-auto is set to `none`; all schema changes MUST be done through Liquibase migrations.
  - Liquibase changelogs are located in `pv-app/src/main/resources/db/changelog/`.

### Database Migrations
New migrations should be added as `.sql` files in `pv-app/src/main/resources/db/changelog/migrations/` and referenced in `db.changelog-migration.xml`.

## Key Files
- `pv-app/pom.xml`: Main application dependencies and configuration.
- `pv-app/src/main/resources/application.properties`: Application configuration including DB credentials and Liquibase settings.
- `pv-app/src/main/resources/db/changelog/db.changelog-migration.xml`: Root Liquibase changelog.
