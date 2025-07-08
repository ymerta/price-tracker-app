package com.ymerta.pricetracker.controller;

import com.ymerta.pricetracker.model.Product;
import com.ymerta.pricetracker.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    // Test endpoint'i
    @GetMapping("/ping")
    public String ping() {
        return "✅ Server is alive!";
    }
}