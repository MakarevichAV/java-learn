package com.sander.warehouse;

import com.sander.warehouse.model.Product;
import com.sander.warehouse.repository.ProductRepository;
import com.sander.warehouse.result.AddProductResult;
import com.sander.warehouse.result.DeleteResult;
import com.sander.warehouse.result.UpdateResult;
import com.sander.warehouse.service.ProductService;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        addDemoProducts(productService);

        UpdateResult quantityUpdateResult = productService.updateProductQuantityById(2, 50);
        UpdateResult nameUpdateResult = productService.updateProductNameById(2, "New Name");
        UpdateResult priceUpdateResult = productService.updateProductPriceById(3, 49.99F);
        System.out.println("Quantity update result: " + quantityUpdateResult);
        System.out.println("Name update result: " + nameUpdateResult);
        System.out.println("Price update result: " + priceUpdateResult);

        UpdateResult sameNameUpdateResult = productService.updateProductNameById(2, "new name");
        System.out.println("Same name update result: " + sameNameUpdateResult);

        System.out.println("Before delete:");

        printAllProducts(productService);

        System.out.println("Service method results before delete:");
        System.out.println("Product by ID: " + productService.findProductById(2));
        System.out.println("Most expensive product: " + productService.findMostExpensiveProduct());
        System.out.println("Total quantity: " + productService.calculateTotalQuantity());
        System.out.println("Products with low stock: " + productService.findProductsWithLowStock(31));
        System.out.println("Budget products: " + productService.findBudgetProducts(50));
        System.out.println("Product by name: " + productService.findProductByName("new name"));

        DeleteResult deleteResult = productService.deleteProductById(1);
        System.out.println("Delete result: " + deleteResult);

        System.out.println("After delete:");

        printAllProducts(productService);
    }

    private static void addDemoProducts(ProductService productService) {
        AddProductResult firstAddResult = productService.addProduct(new Product(1, "Product1", 10, 19.99F));
        AddProductResult secondAddResult = productService.addProduct(new Product(2, "Product2", 20, 29.99F));
        AddProductResult thirdAddResult = productService.addProduct(new Product(3, "Product3", 30, 39.99F));
        AddProductResult duplicateAddResult = productService.addProduct(new Product(3, "Duplicate id", 30, 45.67F));
        System.out.println("First add result: " + firstAddResult);
        System.out.println("Second add result: " + secondAddResult);
        System.out.println("Third add result: " + thirdAddResult);
        System.out.println("Duplicate add result: " + duplicateAddResult);
    }

    private static void printAllProducts(ProductService productService) {
        for (Product product : productService.findAllProducts()) {
            System.out.println(product);
        }
    }
}
