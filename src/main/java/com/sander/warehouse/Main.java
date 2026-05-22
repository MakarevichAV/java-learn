package com.sander.warehouse;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;
import com.sander.warehouse.service.ProductService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        productRepository.addProduct(new Product(1, "Product1", 10, 19.99F));
        productRepository.addProduct(new Product(2, "Product2", 20, 29.99F));
        productRepository.addProduct(new Product(3, "Product3", 30, 39.99F));

        List<Product> products = productRepository.findAll();

        boolean quantityUpdated = productService.updateProductQuantityById(products, 2, 50);
        boolean nameUpdated = productService.updateProductNameById(products, 2, "New Name");
        boolean priceUpdated = productService.updateProductPriceById(products, 3, 49.99F);
        System.out.println("Quantity updated: " + quantityUpdated);
        System.out.println("Name updated: " + nameUpdated);
        System.out.println("Price updated: " + priceUpdated);

        productService.printAllProducts(products);

        boolean deleted = productService.deleteProductById(products, 1);
        System.out.println("Deleted: " + deleted);

        productService.printAllProducts(products);
    }
}
