package com.sander.warehouse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {
    @Test
    void constructorThrowsExceptionWhenIdIsNotPositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(0, "Product1", 10, 19.99F);
        });
    }

    @Test
    void constructorThrowsExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(1, null, 10, 19.99F);
        });
    }

    @Test
    void constructorThrowsExceptionWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(1, "   ", 10, 19.99F);
        });
    }

    @Test
    void constructorThrowsExceptionWhenQuantityIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(1, "Product1", -1, 19.99F);
        });
    }

    @Test
    void constructorThrowsExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(1, "Product1", 10, -1.0F);
        });
    }

    @Test
    void setNameThrowsExceptionWhenNameIsBlank() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        assertThrows(IllegalArgumentException.class, () -> {
            product.setName("   ");
        });
    }

    @Test
    void setQuantityThrowsExceptionWhenQuantityIsNegative() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        assertThrows(IllegalArgumentException.class, () -> {
            product.setQuantity(-1);
        });
    }

    @Test
    void setPriceThrowsExceptionWhenPriceIsNegative() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(-1.0F);
        });
    }

    @Test
    void setNameThrowsExceptionWhenNameIsNull() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        assertThrows(IllegalArgumentException.class, () -> {
            product.setName(null);
        });
    }

    @Test
    void constructorCreatesProductWhenValuesAreValid() {
        Product product = new Product(1, "Product1", 10, 19.99F);

        assertEquals(1, product.getId());
        assertEquals("Product1", product.getName());
        assertEquals(10, product.getQuantity());
        assertEquals(19.99F, product.getPrice(), 0.001F);
    }
}
