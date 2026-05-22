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

    public Product findProductById(List<Product> products, int searchId) {
        for (Product product : products) {
            if (product.getId() == searchId) {
                return product;
            }
        }
        return null;
    }

    public void printAllProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public int calculateTotalQuantity(List<Product> products) {
        int total = 0;
        for (Product product : products) {
            total += product.getQuantity();
        }
        return total;
    }

    public Product findMostExpensiveProduct(List<Product> products) {
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

    public Product findProductByName(List<Product> products, String searchName) {
        for (Product product : products) {
            if (product.getName().equals(searchName)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> findProductsWithLowStock(List<Product> products, int minimumRequiredQuantity) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < minimumRequiredQuantity) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> findProductsWithLowPrice(List<Product> products, float maxPrice) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() < maxPrice) {
                result.add(product);
            }
        }
        return result;
    }

    public void updateProductQuantity(Product product, int newQuantity) {
        product.setQuantity(newQuantity);
    }

    public boolean updateProductQuantityById(List<Product> products, int id, int newQuantity) {
        Product product = findProductById(products, id);
        if (product != null) {
            product.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    public boolean updateProductPriceById(List<Product> products, int id, float newPrice) {
        Product product = findProductById(products, id);
        if (product != null) {
            product.setPrice(newPrice);
            return true;
        }
        return false;
    }

    public boolean updateProductNameById(List<Product> products, int id, String newName) {
        Product product = findProductById(products, id);
        if (product != null) {
            product.setName(newName);
            return true;
        }
        return false;
    }

    public boolean deleteProductById(List<Product> products, int id) {
        Product product = findProductById(products, id);
        if (product != null) {
            products.remove(product);
            return true;
        }
        return false;
    }
}
