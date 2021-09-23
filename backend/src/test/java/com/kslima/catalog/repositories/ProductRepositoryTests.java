package com.kslima.catalog.repositories;

import com.kslima.catalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        long exintingId = 1L;

        productRepository.deleteById(exintingId);

        Optional<Product> result = productRepository.findById(exintingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdNotExists() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            productRepository.deleteById(26L);
        });

    }
}
