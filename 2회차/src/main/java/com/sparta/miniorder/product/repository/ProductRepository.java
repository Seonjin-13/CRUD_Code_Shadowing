package com.sparta.miniorder.product.repository;

import com.sparta.miniorder.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

// <Product, Long>
// - Product : Repository가 관리할 Entity Class
// - Long : Product 엔티티의 기본키 (@Id) Type
