package com.sander.warehouse.service;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;

import java.util.ArrayList;
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
        Product mostExpensiveProduct = products.getFirst();
        for (Product product : products) {
            if (product.getPrice() > mostExpensiveProduct.getPrice()) {
                mostExpensiveProduct = product;
            }
        }
        return mostExpensiveProduct;
    }

    public Product findProductByName(String searchName) {
        for (Product product : productRepository.findAll()) {
            if (product.getName().equals(searchName)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findProductsWithLowStock(int minimumRequiredQuantity) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getQuantity() < minimumRequiredQuantity) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findProductsWithLowPrice(float maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (product.getPrice() < maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public boolean updateProductQuantityById(int id, int newQuantity) {
        Product product = findProductById(id);
        if (product != null) {
            product.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public boolean updateProductPriceById(int id, float newPrice) {
        Product product = findProductById(id);
        if (product != null) {
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean updateProductNameById(int id, String newName) {
        Product product = findProductById(id);
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
