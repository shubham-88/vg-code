# Project Structure

The project is organized as a Spring Boot multi-module Maven project.

## Main Module: `pv-app/`

Located at `pv-app/`, it contains the core application source code.

### Source Packages (`src/main/java/org/vg/pv/app/`)

- `controllers/`: REST API controllers.
- `entities/`: JPA entities representing the database schema.
- `repositories/`: Spring Data JPA repositories.
- `services/`: Business logic, transaction management, and audit field management.
- `jsnobject/`: Custom JSON-related objects or DTOs.

### Resources (`src/main/resources/`)

- `db/changelog/`: Liquibase migration files.
  - `migrations/`: Individual SQL migration scripts.
  - `db.changelog-migration.xml`: Root changelog file.
- `podman-files/`: Configuration for local development environment.
- `application.properties`: Main configuration file.

## Build and Dependencies

- **Build Tool:** Maven (using `mvnw` wrapper).
- **Java Version:** 21.
- **Spring Boot Version:** 4.0.4.
- **Persistence:** Spring Data JPA with PostgreSQL.
