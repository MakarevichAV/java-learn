# Learning Plan

## Goal

Prepare for Junior/Student Java backend interviews by building a Warehouse Management System step by step.

## Current Step

Continue the Warehouse Management System with layered architecture:

```text
Main -> ProductService -> ProductRepository -> List<Product>
```

Current package structure:

```text
src/main/java/com/sander/warehouse/Main.java
src/main/java/com/sander/warehouse/model/Product.java
src/main/java/com/sander/warehouse/service/ProductService.java
src/main/java/com/sander/warehouse/repository/ProductRepository.java
```

## Completed So Far

- Created `Product` model with `id`, `name`, `quantity`, `price`
- Added constructor, getters, setters, and `toString()`
- Added validation for `id`, `name`, `quantity`, and `price`
- Made `id` immutable with `final`
- Practiced `List<Product>` and `ArrayList`
- Added service methods for search, filtering, update, delete, and total quantity
- Practiced object references by updating an object from a list
- Created package structure: `model`, `service`, `repository`
- Created `ProductRepository`
- Moved storage responsibility into `ProductRepository`
- Connected `ProductService` to `ProductRepository` using constructor injection
- Changed `Main` to work mostly through `ProductService`

## Current Topic

Repository encapsulation.

Current question:

```text
Why is returning the internal List<Product> from ProductRepository.findAll() dangerous?
```

Current answer:

Returning the internal list exposes repository state. External code can modify the original list directly, for example by calling `clear()`, `add()`, or `remove()` without using repository methods.

## Next Step

Improve `ProductRepository.findAll()`.

Instead of returning the internal list directly:

```java
return products;
```

learn to return a copy:

```java
return new ArrayList<>(products);
```

This protects the repository list itself from external structural changes.

## Topics To Cover Later

- OOP
- encapsulation
- constructors
- collections
- repository layer
- service layer
- dependency injection basics
- `HashMap`
- `equals()` and `hashCode()`
- unit tests
- design patterns
- singleton
- REST API
- Spring Boot
- SQL
- Docker

## Suggested Workflow

1. Write code manually.
2. Run Maven compile.
3. Read compiler errors.
4. Fix one error at a time.
5. Ask the AI Agent for review, not instant full solution.
6. Commit working steps.

## Useful Prompts For The AI Agent

```text
Read AI_CONTEXT.md and LEARNING_PLAN.md before helping me.
```

```text
Review my Product class like on a junior Java interview.
```

```text
Explain what can be improved in terms of encapsulation and clean code.
```

```text
Give me the next small Java backend task, but do not write the full solution.
```
