# Interview Notes

Use this file as a personal Java backend knowledge base.

## Current Topics

### Encapsulation

Encapsulation means keeping fields private and controlling access through methods.

### Constructor

A constructor creates a valid object and initializes its state.

### Getter

A getter returns the value of a private field.

### Setter

A setter changes the value of a private field. Later, setters can also validate input.

### `toString()`

`toString()` gives a readable text representation of an object, useful for debugging and logging.

### Object Reference

In Java, an object variable stores a reference to an object, not a full independent copy of that object.

If a product is found inside a list and assigned to a variable, changing that product through the variable changes the same object that is still stored in the list.

### `final` Field

A `final` field can be assigned once.

For example, `id` can be `final` because a product id should usually not change after the product is created.

### Validation

A model class should protect its own state.

For example, `Product` validates:

- `id` must be positive
- `name` must not be `null` or blank
- `quantity` must not be negative
- `price` must not be negative

### Service Layer

`ProductService` contains business operations.

Examples:

- add product
- find product
- update product
- delete product
- calculate total quantity
- filter products

### Repository Layer

`ProductRepository` is responsible for storing and retrieving products.

In the current project it stores products in:

```java
private final List<Product> products = new ArrayList<>();
```

Later this idea maps to database access in real backend applications.

### Constructor Injection

`ProductService` receives `ProductRepository` through its constructor:

```java
public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
}
```

This means the service depends on the repository, but does not create it by itself.

### Returning Internal Lists

Returning an internal list directly can be dangerous:

```java
return products;
```

External code can then modify repository data directly:

```java
productRepository.findAll().clear();
```

A safer next step is returning a copy:

```java
return new ArrayList<>(products);
```

This is a shallow copy: the list object is new, but the `Product` objects inside are the same objects.

It protects the repository list from structural changes like `clear()`, `add()`, and `remove()` on the returned list.

It does not protect the product objects themselves from mutation.

### Shallow Copy

A shallow copy copies the container, but not the objects inside it.

Example:

```java
List<Product> copy = new ArrayList<>(products);
```

The `copy` list is a new list, but it contains references to the same `Product` objects.

### Deep Copy

A deep copy would also create new `Product` objects.

This is more advanced and is not implemented yet in the project.

### Repository Naming vs Service Naming

Repository method names usually describe technical queries:

```java
findById
findByName
findByQuantityLessThan
findByPriceLessThan
```

Service method names can describe business meaning:

```java
findProductsWithLowStock
findBudgetProducts
calculateTotalQuantity
findMostExpensiveProduct
```

Example:

```java
findByQuantityLessThan(30)
```

means a technical condition: `quantity < 30`.

```java
findProductsWithLowStock(30)
```

means a warehouse business concept: products that may need restocking.

### Responsibility Rule

Current project rule:

```text
Repository = simple storage and query operations
Service = business logic, updates, calculations, and business naming
```

## Questions To Practice

- Why should fields usually be private?
- What is the difference between a field and a local variable?
- What is a constructor used for?
- Why does every field in Java need a type?
- What should `toString()` include?
- Why can `id` be `final`?
- Why should `Product` validate its own fields?
- What is the responsibility of `ProductService`?
- What is the responsibility of `ProductRepository`?
- Why can returning an internal list directly be dangerous?
- What is the difference between shallow copy and deep copy?
- Why can service method names be different from repository method names?
- Why is `findProductsWithLowStock` more business-oriented than `findByQuantityLessThan`?
