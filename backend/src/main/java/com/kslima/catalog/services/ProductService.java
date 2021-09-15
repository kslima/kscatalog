package com.kslima.catalog.services;

import com.kslima.catalog.dto.ProductDTO;
import com.kslima.catalog.entities.Product;
import com.kslima.catalog.repositories.ProductRepository;
import com.kslima.catalog.services.exceptions.DatabaseException;
import com.kslima.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        return repository.findAll(pageRequest)
                .map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> opt = repository.findById(id);
        return opt.map(prod -> new ProductDTO(prod, prod.getCategories()))
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
    }

    @Transactional()
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getOne(id);
            //entity.setName(dto.getName());
            entity = repository.save(entity);
            return new ProductDTO(entity);
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
