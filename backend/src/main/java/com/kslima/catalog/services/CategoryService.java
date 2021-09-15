package com.kslima.catalog.services;

import com.kslima.catalog.dto.CategoryDTO;
import com.kslima.catalog.entities.Category;
import com.kslima.catalog.repositories.CategoryRepository;
import com.kslima.catalog.services.exceptions.DatabaseException;
import com.kslima.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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
        return opt.map(CategoryDTO::new).orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    @Transactional()
    public CategoryDTO insert(CategoryDTO dto) {
        Category category = new Category(dto);
        repository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category category = repository.getOne(id);
            category.setName(dto.getName());
            category = repository.save(category);
            return new CategoryDTO(category);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }

    }
}
