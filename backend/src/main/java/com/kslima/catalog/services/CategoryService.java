package com.kslima.catalog.services;

import com.kslima.catalog.dto.CategoryDTO;
import com.kslima.catalog.entities.Category;
import com.kslima.catalog.repositories.CategoryRepository;
import com.kslima.catalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        return repository.findAll().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> opt = repository.findById(id);
        return opt.map(CategoryDTO::new).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
