package com.kslima.catalog.repositories;

import com.kslima.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
