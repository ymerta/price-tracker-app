package com.ymerta.pricetracker.repository;

import com.ymerta.pricetracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}