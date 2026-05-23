package com.sander.warehouse.service;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void printAllProducts() {
        for (Product product : productRepository.findAll()) {
            System.out.println(product);
        }
    }

    public Product findProductById(int id) {
        return productRepository.findById(id);
    }

    public int calculateTotalQuantity() {
        int total = 0;
        for (Product product : productRepository.findAll()) {
            total += product.getQuantity();
        }
        return total;
    }

    public Product findMostExpensiveProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return null;
        }
        Product mostExpensiveProduct = products.get(0);
        for (Product product : products) {
            if (product.getPrice() > mostExpensiveProduct.getPrice()) {
                mostExpensiveProduct = product;
            }
        }
        return mostExpensiveProduct;
    }

    public Product findProductByName(String searchName) {
        return productRepository.findByName(searchName);
    }

    public List<Product> findProductsWithLowStock(int minRequiredQuantity) {
        return productRepository.findByQuantityLessThan(minRequiredQuantity);
    }

    public List<Product> findBudgetProducts(float maxPrice) {
        return productRepository.findByPriceLessThan(maxPrice);
    }

    public boolean updateProductQuantityById(int id, int newQuantity) {
        Product product = productRepository.findById(id);
        if (product != null) {
            product.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public boolean updateProductPriceById(int id, float newPrice) {
        Product product = productRepository.findById(id);
        if (product != null) {
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean updateProductNameById(int id, String newName) {
        Product product = productRepository.findById(id);
        if (product != null) {
            product.setName(newName);
            return true;
        }
        return false;
    }

    public boolean deleteProductById(int id) {
        return productRepository.deleteById(id);
    }
}
