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
- Changed `ProductRepository.findAll()` to return a shallow copy
- Practiced repository encapsulation
- Discussed shallow copy vs deep copy
- Moved simple query methods to `ProductRepository`
- Kept business operations in `ProductService`
- Practiced naming differences between repository query methods and service business methods

## Current Topic

Repository/service responsibility and method naming.

Current rule:

```text
Repository = technical data/query operations
Service = business operations and business language
```

Examples:

```java
// Repository
findByQuantityLessThan(int quantity)
findByPriceLessThan(float price)

// Service
findProductsWithLowStock(int minimumRequiredQuantity)
findBudgetProducts(float maxPrice)
```

## Next Step

In `Main`, call the service methods:

```java
System.out.println("Low stock products: " + productService.findProductsWithLowStock(30));
System.out.println("Budget products: " + productService.findBudgetProducts(30.0F));
```

Put these calls before delete, so the list is still complete.

After that, continue cleaning method names and responsibilities.

Estimated progress: 35-40%.

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
