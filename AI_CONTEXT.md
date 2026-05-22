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

Estimated progress toward Student/Junior Java backend readiness through this project: about 30-35%.

Current topic:

```text
Repository layer and encapsulation of stored data
```

The next learning step is to improve `ProductRepository.findAll()`.
Right now it returns the internal list directly, which is useful for learning but unsafe because external code can modify repository data without using repository methods.

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
