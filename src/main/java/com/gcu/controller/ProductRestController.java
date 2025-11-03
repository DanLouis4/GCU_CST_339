package com.gcu.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gcu.business.ProductServiceInterface;
import com.gcu.model.ProductModel;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")  // Allow frontend apps to connect (React, Angular, etc.)
public class ProductRestController {

    @Autowired
    private ProductServiceInterface productService;

    // ✅ 1. Get all products
    @GetMapping
    public List<ProductModel> getAllProducts() {
        return productService.findAll();
    }

    // ✅ 2. Get products by restaurant ID
    @GetMapping("/restaurant/{restaurantId}")
    public List<ProductModel> getProductsByRestaurant(@PathVariable int restaurantId) {
        return productService.findByRestaurantId(restaurantId);
    }

    // ✅ 3. Get single product by ID
    @GetMapping("/{id}")
    public ProductModel getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    // ✅ 4. Create a new product
    @PostMapping
    public String createProduct(@RequestBody ProductModel product) {
        boolean success = productService.create(product);
        return success ? "Product created successfully" : "Product creation failed";
    }

    // ✅ 5. Update an existing product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody ProductModel product) {
        product.setId(id);
        boolean success = productService.update(product);
        return success ? "Product updated successfully" : "Product update failed";
    }

    // ✅ 6. Delete a product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        ProductModel product = productService.findById(id);
        if (product == null) return "Product not found";
        boolean success = productService.delete(product);
        return success ? "Product deleted successfully" : "Product deletion failed";
    }
}
