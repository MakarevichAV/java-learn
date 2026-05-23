package com.sander.warehouse.repository;

import com.sander.warehouse.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        Product product = findById(id);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }

    public Product findByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findByQuantityLessThan(int minRequiredQuantity) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < minRequiredQuantity) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findByPriceLessThan(float maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() < maxPrice) {
                result.add(product);
            }
        }
        return result;
    }
}
