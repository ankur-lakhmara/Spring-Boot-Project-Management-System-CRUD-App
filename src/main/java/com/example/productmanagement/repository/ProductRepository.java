package com.example.productmanagement.repository;

import com.example.productmanagement.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {
}
