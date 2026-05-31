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

### Case-Sensitive vs Case-Insensitive Search

`equals()` compares strings exactly:

```java
product.getName().equals(name)
```

`equalsIgnoreCase()` compares strings without caring about letter case:

```java
product.getName().equalsIgnoreCase(name)
```

Example:

```java
"New Name".equalsIgnoreCase("new name")
```

returns `true`.

This is useful when product search should be user-friendly.

### Order of Operations

The order of method calls matters.

If `deleteProductById(1)` is called before `calculateTotalQuantity()`, then the total quantity is calculated after product `1` has already been removed.

For clean demo code, separate output into sections:

```text
Before delete
Service method results before delete
After delete
```

This makes behavior easier to understand and explain in an interview.

### Enum

An `enum` is a Java type for a fixed set of possible values.

Example:

```java
public enum UpdateResult {
    UPDATED,
    NOT_FOUND,
    NO_CHANGE
}
```

A variable of type `UpdateResult` can only contain one of these defined values.

This is safer than using strings because typos become compiler errors.

### Boolean vs Enum Result

A `boolean` result can be too vague.

Example:

```java
false
```

could mean:

- product was not found
- value was the same
- update failed for another reason

An enum can communicate the exact result:

```java
UpdateResult.UPDATED
UpdateResult.NOT_FOUND
UpdateResult.NO_CHANGE
```

This makes service methods more informative.

The project also has:

```java
public enum DeleteResult {
    DELETED,
    NOT_FOUND
}
```

This makes delete results more explicit than returning only `true` or `false`.

The project also has:

```java
public enum AddProductResult {
    ADDED,
    ALREADY_EXISTS
}
```

This prevents duplicate product ids from being silently overwritten.

### Guard Clause

A guard clause exits a method early when work cannot continue.

Example:

```java
if (product == null) {
    return UpdateResult.NOT_FOUND;
}

if (product.getQuantity() == newQuantity) {
    return UpdateResult.NO_CHANGE;
}

product.setQuantity(newQuantity);
return UpdateResult.UPDATED;
```

This avoids deep nested `if` blocks and keeps the main successful path easy to read.

### Service Should Not Print

In real backend code, a service should usually not print to the console.

The service should return data:

```java
public List<Product> findAllProducts()
```

The caller decides how to display it.

In this project:

```text
ProductService = returns data and result values
Main = prints output for demo purposes
```

This prepares for Spring Boot, where:

```text
Service = business logic
Controller = HTTP input/output
```

### Private Static Helper in Main

A helper method in `Main` can be:

```java
private static
```

`private` means it is only used inside `Main`.

`static` means it can be called from the static `main` method without creating a `Main` object.

### Map and HashMap

`Map` is an interface for key-value storage.

`HashMap` is a concrete implementation of `Map`.

Example:

```java
private final Map<Integer, Product> productMap = new HashMap<>();
```

This means:

```text
Map<Integer, Product> = variable type / interface
HashMap<> = concrete implementation
Integer = key type
Product = value type
```

The repository can find a product by id quickly:

```java
productMap.get(id)
```

### List vs Map

`List<Product>` stores products by order/index.

`Map<Integer, Product>` stores products by key-value pair.

For this project:

```text
key = product id
value = Product object
```

`List` search by id usually requires a loop.

`Map` search by id uses the key directly.

### HashMap vs LinkedHashMap

`HashMap` does not guarantee insertion order.

`LinkedHashMap` preserves insertion order.

If order does not matter, `HashMap` is fine.

If predictable output order matters, `LinkedHashMap` can be used while keeping the field type as `Map`.

### Program To Interface

Prefer:

```java
Map<Integer, Product> productMap = new HashMap<>();
```

instead of:

```java
HashMap<Integer, Product> productMap = new HashMap<>();
```

This lets the implementation change later:

```java
Map<Integer, Product> productMap = new LinkedHashMap<>();
```

without changing the rest of the code that only needs `Map` behavior.

### Generics and Wrapper Types

`Map<Integer, Product>` means:

```text
Integer = key type
Product = value type
```

Java generics cannot use primitive types like `int`.

Use wrapper types:

```text
int -> Integer
float -> Float
double -> Double
boolean -> Boolean
```

Java can automatically convert `int` to `Integer`. This is called autoboxing.

Java can automatically convert `Integer` back to `int`. This is called unboxing.

### Single Source Of Truth

Avoid storing the same data in two structures unless there is a strong reason.

The repository briefly had both:

```java
List<Product> products
Map<Integer, Product> productMap
```

This caused a sync problem: deleting from one structure but not the other creates bugs.

Current repository uses `Map<Integer, Product>` as the single source of truth.

### Maven and JUnit

Maven is a Java build and dependency management tool.

`pom.xml` is similar in purpose to `package.json` in JavaScript projects.

JUnit Jupiter is used for unit tests.

Tests live under:

```text
src/test/java
```

### Unit Test Structure

Common test structure:

```text
Arrange = prepare objects and data
Act = call the method being tested
Assert = check the result
```

Example:

```java
Product product = new Product(1, "Product1", 10, 19.99F);

AddProductResult result = productService.addProduct(product);

assertEquals(AddProductResult.ADDED, result);
```

### `@BeforeEach`

`@BeforeEach` runs before every test method.

It is used to create fresh test state:

```java
@BeforeEach
void setUp() {
    productRepository = new ProductRepository();
    productService = new ProductService(productRepository);
}
```

This prevents tests from affecting each other.

### `assertEquals`

`assertEquals(expected, actual)` checks that expected and actual values are equal.

Example:

```java
assertEquals(AddProductResult.ADDED, result);
```

The usual argument order is:

```text
expected first, actual second
```

### `assertThrows`

`assertThrows` checks that code throws the expected exception type.

Example:

```java
assertThrows(IllegalArgumentException.class, () -> {
    new Product(0, "Product1", 10, 19.99F);
});
```

`IllegalArgumentException.class` is the expected exception type.

The lambda is code that JUnit executes during the assertion.

### Lambda

A lambda is a piece of code that can be passed as a value and executed later.

Java:

```java
() -> {
    new Product(0, "Product1", 10, 19.99F);
}
```

This is similar to JavaScript arrow functions:

```js
() => {
  // code
}
```

### Floating Point Comparison

`float` and `double` values can have small precision errors.

In tests, compare them with a delta:

```java
assertEquals(19.99F, actualPrice, 0.001F);
```

The third argument is the allowed difference between expected and actual values.

For money in real projects, `BigDecimal` is usually better than `float`.

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
- What is the difference between `equals()` and `equalsIgnoreCase()`?
- Why does the order of update/delete/read operations matter?
- What is an enum?
- Why is enum better than String for fixed result values?
- Why can boolean be too vague for update results?
- What is a guard clause?
- Why should service methods usually not print to the console?
- Why can `Main` contain private static helper methods in a demo application?
- What is the difference between returning data and printing data?
- What is the difference between `List<Product>` and `Map<Integer, Product>`?
- Why does `Map<Integer, Product>` use `Integer` instead of `int`?
- What is autoboxing?
- What is the difference between `Map` and `HashMap`?
- Why is it useful to declare a variable as `Map` instead of `HashMap`?
- Why can duplicate storage in both `List` and `Map` cause bugs?
- Why does `HashMap.put()` need a business rule in service to prevent duplicate ids?
- What is Maven used for?
- What is JUnit used for?
- What are Arrange, Act, and Assert?
- Why does `@BeforeEach` create fresh objects for every test?
- What does `assertEquals(expected, actual)` check?
- What does `assertThrows` check?
- Why does `assertThrows` receive `IllegalArgumentException.class`?
- What is a lambda?
- Why do `float` and `double` tests often use a delta?
- Why is `BigDecimal` usually better than `float` for money?
