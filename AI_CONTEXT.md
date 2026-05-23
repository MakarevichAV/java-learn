# AI Context

I am learning Java backend development through a real project.

## Background

I am transitioning from industrial automation to Java backend development.

## Current Goal

Become confident for Junior/Student Java backend interviews.

## Project

Warehouse Management System.

## Current Focus

Core Java fundamentals and basic backend architecture through hands-on project development:

- project structure
- package naming
- encapsulation
- classes and objects
- constructors
- getters and setters
- `toString()`
- `List<Product>` and `ArrayList`
- service methods
- repository layer
- CRUD-style operations
- object references
- validation
- clean code basics

## Current Project State

The project has moved from a single-package structure to a layered package structure:

```text
com.sander.warehouse
com.sander.warehouse.model
com.sander.warehouse.service
com.sander.warehouse.repository
```

Current main classes:

- `Product` in `com.sander.warehouse.model`
- `ProductService` in `com.sander.warehouse.service`
- `ProductRepository` in `com.sander.warehouse.repository`
- `Main` in `com.sander.warehouse`

Current architecture:

```text
Main -> ProductService -> ProductRepository -> List<Product>
```

## Current Learning Position

Estimated progress toward Student/Junior Java backend readiness through this project: about 40%.

Current topic:

```text
Repository/service responsibility, service method usage, and demo flow in Main
```

Recently completed:

- Improved `ProductRepository.findAll()` to return a shallow copy using `new ArrayList<>(products)`
- Discussed shallow copy vs deep copy
- Moved simple query methods into `ProductRepository`
- Kept business/operation methods in `ProductService`
- Discussed repository-style method names vs service/business method names
- Called all remaining `ProductService` methods from `Main`
- Added case-insensitive product name search using `equalsIgnoreCase`

Current naming rule:

```text
Repository = technical query names, for example findByQuantityLessThan
Service = business names, for example findProductsWithLowStock
```

Current code state:

- `Main` creates `ProductRepository`
- `Main` creates `ProductService` with constructor injection
- `Main` adds products through `ProductService`
- `Main` calls update, delete, print, find by id, find by name, total quantity, most expensive product, low stock, and budget product methods

The next learning step is to clean the output/demo flow in `Main`: separate "before delete" and "after delete" operations so it is clear which methods work on the full list and which methods work after deletion.

## How The AI Agent Should Help

- Do not generate full solutions immediately.
- Act as a mentor, reviewer, and interviewer.
- Explain concepts step by step.
- Give small progressive tasks.
- Review my code like a junior Java interview.
- Ask interview-style questions.
- Encourage manual coding and debugging.
- Prefer explanations that help me understand why Java works this way.

## Important Learning Rule

The goal is not to finish the project as fast as possible.

The goal is to start thinking like a Java backend developer:

- structure
- naming
- encapsulation
- objects
- clean code

It is okay if the code is slow, imperfect, or ugly at first. Growth comes from writing code manually, breaking it, fixing it, and understanding why it works.
