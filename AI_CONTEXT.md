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

Estimated progress toward Student/Junior Java backend readiness through this project: about 55%.

Current topic:

```text
JUnit tests, model validation tests, and preparation for equals/hashCode
```

Recently completed:

- Improved `ProductRepository.findAll()` to return a shallow copy using `new ArrayList<>(products)`
- Discussed shallow copy vs deep copy
- Moved simple query methods into `ProductRepository`
- Kept business/operation methods in `ProductService`
- Discussed repository-style method names vs service/business method names
- Called all remaining `ProductService` methods from `Main`
- Added case-insensitive product name search using `equalsIgnoreCase`
- Cleaned `Main` output into before/delete/after sections
- Added `UpdateResult` enum in `com.sander.warehouse.result`
- Replaced boolean update results with `UpdateResult` for quantity, price, and name updates
- Practiced guard clauses in update methods
- Added `DeleteResult` enum with `DELETED` and `NOT_FOUND`
- Changed `deleteProductById` to return `DeleteResult`
- Added `findAllProducts()` to `ProductService`
- Removed console output responsibility from `ProductService`
- Replaced `printAllProducts()` service method with `Main` output logic
- Added a private static helper method in `Main` for printing products
- Extracted demo product setup into `addDemoProducts(ProductService productService)`
- Changed `ProductRepository` from `List<Product>` storage to `Map<Integer, Product>` storage
- `ProductRepository.findAll()` now returns `new ArrayList<>(productMap.values())`
- Discussed `HashMap` vs `LinkedHashMap`
- Discussed `Map` interface vs `HashMap` implementation
- Discussed generics and why `Map<Integer, Product>` uses `Integer`, not `int`
- Added `AddProductResult` enum with `ADDED` and `ALREADY_EXISTS`
- Added duplicate id protection in `ProductService.addProduct`
- Added Maven/JUnit Jupiter test setup
- Created `ProductServiceTest`
- Added service unit tests for add, duplicate id, update quantity, delete, find, filters, analytics, and empty repository
- Created `ProductTest`
- Added constructor and setter validation tests for `Product`
- Discussed `assertEquals`, `assertThrows`, lambda syntax, `IllegalArgumentException.class`
- Discussed comparing `float` values in tests with a delta

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
- update methods now return `UpdateResult`: `UPDATED`, `NOT_FOUND`, or `NO_CHANGE`
- delete method now returns `DeleteResult`: `DELETED` or `NOT_FOUND`
- `ProductService` returns data; `Main` decides how to print it
- add method now returns `AddProductResult`: `ADDED` or `ALREADY_EXISTS`
- repository storage is now `private final Map<Integer, Product> productMap = new HashMap<>()`
- tests are under `src/test/java`
- current testing framework is JUnit Jupiter

The next learning step is to continue from unit tests toward `equals()` and `hashCode()`, and then clean up `Main` now that behavior is covered by tests.

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
