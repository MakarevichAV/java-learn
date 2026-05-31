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
- Called all current `ProductService` methods from `Main`
- Added case-insensitive search by product name with `equalsIgnoreCase`
- Cleaned `Main` output into clear sections
- Added `UpdateResult` enum
- Replaced ambiguous boolean update results with `UpdateResult`
- Practiced guard clauses in update methods
- Added `DeleteResult` enum
- Changed delete operation to return `DeleteResult`
- Added `findAllProducts()` to `ProductService`
- Removed console printing from `ProductService`
- Moved product printing responsibility to `Main`
- Added private static helper method in `Main` for printing products
- Extracted demo product setup into `addDemoProducts(ProductService productService)`
- Replaced repository `List<Product>` storage with `Map<Integer, Product>`
- Made `Map<Integer, Product>` the single source of truth in `ProductRepository`
- Added duplicate id protection through `AddProductResult`
- Discussed `HashMap`, `LinkedHashMap`, `Map` interface, generics, and wrapper types
- Added JUnit Jupiter to Maven
- Created `ProductServiceTest`
- Added unit tests for add, duplicate id, update quantity, delete, find, filters, analytics, and empty repository
- Created `ProductTest`
- Added validation tests for `Product` constructor and setters
- Discussed `assertEquals`, `assertThrows`, lambda syntax, exception class references, and float delta

## Current Topic

JUnit testing and preparation for `equals()` / `hashCode()`.

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

## Current State

Current result enums:

```java
public enum UpdateResult {
    UPDATED,
    NOT_FOUND,
    NO_CHANGE
}

public enum DeleteResult {
    DELETED,
    NOT_FOUND
}

public enum AddProductResult {
    ADDED,
    ALREADY_EXISTS
}
```

Current service responsibility:

```text
ProductService returns data and result values.
Main decides how to print output.
```

`ProductService` now has:

```java
public List<Product> findAllProducts()
```

Current repository storage:

```java
private final Map<Integer, Product> productMap = new HashMap<>();
```

Current repository idea:

```text
Map<Integer, Product> = storage
Integer = product id key
Product = stored product value
```

Current test structure:

```text
src/test/java/com/sander/warehouse/service/ProductServiceTest.java
src/test/java/com/sander/warehouse/model/ProductTest.java
```

Current test coverage:

```text
ProductService:
- add product
- duplicate id
- update quantity
- delete
- find by id/name
- filters
- total quantity
- most expensive product
- empty repository

Product:
- valid constructor
- invalid constructor values
- invalid setter values
```

## Next Step

Continue toward:

```text
equals() and hashCode()
```

Planned next learning path:

1. Explain why Java objects need `equals()` and `hashCode()`
2. Add focused tests that show default object comparison behavior
3. Implement `equals()` and `hashCode()` in `Product`
4. Discuss how `HashMap` and `HashSet` use `hashCode()` and `equals()`
5. Clean up `Main`, because most behavior is now covered by tests

Estimated progress: about 55%.

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
