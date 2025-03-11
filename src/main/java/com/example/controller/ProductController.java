package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.Product;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/products") // Updated to match product-related operations
public class ProductController { // Fixed typo in class name
    
    @Autowired
    private ProductService productService; // Renamed for clarity

    // Create a new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) { // Fixed parameter name
        return productService.createProduct(product);
    }

    // Get all products
    @GetMapping("/list")
    public List<Product> getProducts() { // Renamed for better readability
        return productService.getAllProducts();
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product productDetails) { // Fixed method signature
        Optional<Product> existingProduct = productService.getProductByid(id); // Fixed variable name

        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(productDetails.getName());
            product.setDescription(productDetails.getDescription());
            product.setPrice(productDetails.getPrice());

            Product updatedProduct = productService.updateProduct(product); // Fixed method call
            return ResponseEntity.ok(updatedProduct);
        }
        return ResponseEntity.notFound().build();
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) { // Fixed method name
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
