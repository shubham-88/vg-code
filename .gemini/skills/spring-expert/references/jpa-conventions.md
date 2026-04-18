# JPA and Database Conventions

This project uses Spring Data JPA with PostgreSQL and Liquibase for schema management.

## Entity Design

### Annotations

- **Entity:** `@Entity` at the class level.
- **Table:** `@Table(name = "table_name", schema = "postgres-vg-sc1")`.
- **Primary Key:** `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
- **Columns:** `@Column` for database column mapping. Use `@Column(name = "column_name")`.

### Audit Fields

Every entity should have:
- `createdAt` (`LocalDateTime`)
- `updatedAt` (`LocalDateTime`)
- Managed manually in the service layer (this project does not use `@CreatedDate` / `@LastModifiedDate` annotations).

### No Lombok

Manually implement:
- Getters and Setters.
- Constructors (including a no-args constructor for JPA).
- `toString()`, `equals()`, and `hashCode()` where appropriate.

## Schema Migrations

- All schema changes MUST be done using SQL files in `pv-app/src/main/resources/db/changelog/migrations/`.
- New migrations should be added as `.sql` files and registered in `db.changelog-migration.xml`.
- Schema name: `postgres-vg-sc1`.

## Database Interaction

- Use Spring Data JPA Repositories (interfaces extending `JpaRepository`).
- Prefer derived query methods where possible.
- Use `@Query` for complex queries.
