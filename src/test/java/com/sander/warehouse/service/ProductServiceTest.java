package com.sander.warehouse.service;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;
import com.sander.warehouse.result.AddProductResult;
import com.sander.warehouse.result.DeleteResult;
import com.sander.warehouse.result.UpdateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductServiceTest {
    private ProductRepository productRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productRepository = new ProductRepository();
        productService = new ProductService(productRepository);
    }

    @Test
    void addProductReturnsAddedWhenIdIsNew() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        AddProductResult result = productService.addProduct(product);

        assertEquals(AddProductResult.ADDED, result);
    }

    @Test
    void addProductReturnsAlreadyExistsWhenIdAlreadyExists() {
        Product firstProduct = new Product(1, "Product1", 10, 49.99F);
        Product secondProduct = new Product(1, "Product2", 10, 49.99F);
        productService.addProduct(firstProduct);

        AddProductResult result = productService.addProduct(secondProduct);

        assertEquals(AddProductResult.ALREADY_EXISTS, result);
    }

    @Test
    void addProductDoesNotOverwriteExistingProductWhenIdAlreadyExists() {
        Product firstProduct = new Product(1, "Product1", 10, 49.99F);
        Product secondProduct = new Product(1, "Product2", 10, 49.99F);
        productService.addProduct(firstProduct);
        productService.addProduct(secondProduct);

        String foundProductName = productService.findProductById(1).getName();

        assertEquals("Product1", foundProductName);
    }

    @Test
    void updateProductQuantityReturnsUpdatedWhenProductExistsAndValueIsDifferent() {
        Product product = new Product(1, "Product1", 10, 19.99F);
        productService.addProduct(product);

        UpdateResult result = productService.updateProductQuantityById(1, 20);

        assertEquals(UpdateResult.UPDATED, result);
        assertEquals(20, productService.findProductById(1).getQuantity());
    }

    @Test
    void updateProductQuantityReturnsNotFoundWhenProductDoesNotExist() {
        UpdateResult result = productService.updateProductQuantityById(99, 20);

        assertEquals(UpdateResult.NOT_FOUND, result);
    }

    @Test
    void updateProductQuantityReturnsNoChangeWhenValueIsTheSame() {
        Product product = new Product(1, "Product1", 10, 19.99F);
        productService.addProduct(product);

        UpdateResult result = productService.updateProductQuantityById(1, 10);

        assertEquals(UpdateResult.NO_CHANGE, result);
        assertEquals(10, productService.findProductById(1).getQuantity());
    }

    @Test
    void deleteProductReturnsDeletedWhenProductExists() {
        Product product = new Product(1, "Product1", 10, 19.99F);
        productService.addProduct(product);

        DeleteResult result = productService.deleteProductById(1);

        assertEquals(DeleteResult.DELETED, result);
        assertNull(productService.findProductById(1));
    }

    @Test
    void deleteProductReturnsNotFoundWhenProductDoesNotExist() {
        DeleteResult result = productService.deleteProductById(99);

        assertEquals(DeleteResult.NOT_FOUND, result);
    }

    @Test
    void findProductByIdReturnsProductWhenProductExists() {
        Product product = new Product(1, "Product1", 10, 19.99F);
        productService.addProduct(product);

        Product foundProduct = productService.findProductById(1);

        assertEquals("Product1", foundProduct.getName());
    }

    @Test
    void findProductByIdReturnsNullWhenProductDoesNotExist() {
        Product foundProduct = productService.findProductById(99);

        assertNull(foundProduct);
    }

    @Test
    void findProductByNameIgnoresCase() {
        Product product = new Product(1, "Product1", 10, 19.99F);
        productService.addProduct(product);

        Product foundProduct = productService.findProductByName("product1");

        assertEquals(1, foundProduct.getId());
    }

    @Test
    void calculateTotalQuantityReturnsSumOfAllProductQuantities() {
        productService.addProduct(new Product(1, "Product1", 10, 19.99F));
        productService.addProduct(new Product(2, "Product2", 20, 29.99F));

        int totalQuantity = productService.calculateTotalQuantity();

        assertEquals(30, totalQuantity);
    }

    @Test
    void findMostExpensiveProductReturnsProductWithHighestPrice() {
        productService.addProduct(new Product(1, "Cheap", 10, 9.99F));
        productService.addProduct(new Product(2, "Expensive", 5, 99.99F));

        Product mostExpensiveProduct = productService.findMostExpensiveProduct();

        assertEquals("Expensive", mostExpensiveProduct.getName());
    }

    @Test
    void findProductsWithLowStockReturnsProductsBelowMinimumRequiredQuantity() {
        productService.addProduct(new Product(1, "Low", 5, 19.99F));
        productService.addProduct(new Product(2, "Enough", 20, 29.99F));

        List<Product> lowStockProducts = productService.findProductsWithLowStock(10);

        assertEquals(1, lowStockProducts.size());
        assertEquals("Low", lowStockProducts.get(0).getName());
    }

    @Test
    void findBudgetProductsReturnsProductsBelowMaxPrice() {
        productService.addProduct(new Product(1, "Budget", 10, 9.99F));
        productService.addProduct(new Product(2, "Premium", 5, 99.99F));

        List<Product> budgetProducts = productService.findBudgetProducts(50.0F);

        assertEquals(1, budgetProducts.size());
        assertEquals("Budget", budgetProducts.get(0).getName());
    }

    @Test
    void calculateTotalQuantityReturnsZeroWhenRepositoryIsEmpty() {
        int totalQuantity = productService.calculateTotalQuantity();

        assertEquals(0, totalQuantity);
    }

    @Test
    void findMostExpensiveProductReturnsNullWhenRepositoryIsEmpty() {
        Product mostExpensiveProduct = productService.findMostExpensiveProduct();

        assertNull(mostExpensiveProduct);
    }

    @Test
    void findAllProductsReturnsEmptyListWhenRepositoryIsEmpty() {
        List<Product> products = productService.findAllProducts();

        assertEquals(0, products.size());
    }
}
