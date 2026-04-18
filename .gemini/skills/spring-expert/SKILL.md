---
name: spring-expert
description: Expert guidance for Spring Boot 4.0.4 development with Java 21, JPA, PostgreSQL, and Liquibase. Use when building or refactoring Spring components, designing entities, or managing database migrations.
---

# Spring Boot Expert

Expert procedural knowledge for developing enterprise-grade Spring Boot applications with Java 21.

## Core Principles

- **Constructor Injection:** Always prefer constructor injection over `@Autowired` on fields.
- **No Lombok:** Do not use Lombok. Use manual getters, setters, and constructors.
- **Java 21 Features:** Utilize modern Java 21 features (records, text blocks, enhanced switch, etc.).
- **RESTful Design:** Return `ResponseEntity` with appropriate HTTP status codes.

## Database & Persistence

- **Liquibase Migrations:** All schema changes MUST be done via Liquibase migrations.
- **JPA Entities:** Use Jakarta Persistence annotations.
- **Audit Fields:** Entities should include `createdAt` and `updatedAt` managed in the service layer.

## Project Structure

Refer to [project-structure.md](references/project-structure.md) for module and package organization.
Refer to [jpa-conventions.md](references/jpa-conventions.md) for detailed entity design patterns.

## Specialized Workflows

- **CRUD Generation:** Refer to [create-curd-controller.md](references/create-curd-controller.md) when asked to generate a new controller with full CRUD operations.

