package com.sander.warehouse.service;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;
import com.sander.warehouse.result.AddProductResult;
import com.sander.warehouse.result.DeleteResult;
import com.sander.warehouse.result.UpdateResult;

import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public AddProductResult addProduct(Product product) {
        if (productRepository.findById(product.getId()) != null) {
            return AddProductResult.ALREADY_EXISTS;
        }
        productRepository.addProduct(product);
        return AddProductResult.ADDED;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(int id) {
        return productRepository.findById(id);
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

    public int calculateTotalQuantity() {
        int total = 0;
        for (Product product : productRepository.findAll()) {
            total += product.getQuantity();
        }
        return total;
    }

    public UpdateResult updateProductQuantityById(int id, int newQuantity) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return UpdateResult.NOT_FOUND;
        }
        if (product.getQuantity() == newQuantity) {
            return UpdateResult.NO_CHANGE;
        }
        product.setQuantity(newQuantity);
        return UpdateResult.UPDATED;
    }

    public UpdateResult updateProductPriceById(int id, float newPrice) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return UpdateResult.NOT_FOUND;
        }
        if (product.getPrice() == newPrice) {
            return UpdateResult.NO_CHANGE;
        }
        product.setPrice(newPrice);
        return UpdateResult.UPDATED;
    }

    public UpdateResult updateProductNameById(int id, String newName) {
        Product product = productRepository.findById(id);
        if (product == null) {
            return UpdateResult.NOT_FOUND;
        }
        if (product.getName().equalsIgnoreCase(newName)) {
            return UpdateResult.NO_CHANGE;
        }
        product.setName(newName);
        return UpdateResult.UPDATED;
    }

    public DeleteResult deleteProductById(int id) {
        return productRepository.deleteById(id) ? DeleteResult.DELETED : DeleteResult.NOT_FOUND;
    }

}
