package com.shopease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final List<Map<String, Object>> products = new ArrayList<>(List.of(
        Map.of("id", 1, "name", "iPhone 15", "price", 79999, "stock", 50),
        Map.of("id", 2, "name", "MacBook Pro", "price", 199999, "stock", 20),
        Map.of("id", 3, "name", "AirPods Pro", "price", 24999, "stock", 100)
    ));

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllProducts() {
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable int id) {
        return products.stream()
            .filter(p -> p.get("id").equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "UP",
            "service", "shopease-product-api",
            "version", "1.0.0"
        ));
    }
}