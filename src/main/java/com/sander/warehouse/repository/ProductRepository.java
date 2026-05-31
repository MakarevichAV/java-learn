package com.sander.warehouse.repository;

import com.sander.warehouse.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {

    private final Map<Integer, Product> productMap = new HashMap<>();

    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(int id) {
        return productMap.get(id);
    }

    public boolean deleteById(int id) {
        Product removedProduct = productMap.remove(id);
        return removedProduct != null;
    }

    public Product findByName(String name) {
        for (Product product : productMap.values()) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findByQuantityLessThan(int minRequiredQuantity) {
        List<Product> result = new ArrayList<>();
        for (Product product : productMap.values()) {
            if (product.getQuantity() < minRequiredQuantity) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findByPriceLessThan(float maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : productMap.values()) {
            if (product.getPrice() < maxPrice) {
                result.add(product);
            }
        }
        return result;
    }
}
