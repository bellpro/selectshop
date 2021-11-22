package com.bellpro.selectshop.repository;

import com.bellpro.selectshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }