package org.polimi.nexbuy.service.impl.products;


import org.polimi.nexbuy.exception.products.CategoryAlreadyExistsException;
import org.polimi.nexbuy.exception.products.CategoryNotFoundException;
import org.polimi.nexbuy.model.products.Category;
import org.polimi.nexbuy.repository.products.CategoryRepository;
import org.polimi.nexbuy.service.def.products.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(long Id) {
        return Optional.ofNullable(categoryRepository.findById(Id).orElseThrow(() -> new CategoryNotFoundException("Categoria con ID " + Id + " non trovata")));
    }

    public Category addCategory(Category category) {
        if (categoryRepository.existsById(category.getId())) {
            throw new CategoryAlreadyExistsException("Categoria con ID " + category.getId() + " esiste già.");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        if (!categoryRepository.existsById(category.getId())) {
            throw new CategoryNotFoundException("Categoria con ID " + category.getId() + " non trovata per l'aggiornamento.");
        }
        return categoryRepository.save(category);
    }

    public void deleteCategory(long Id) {
        if (!categoryRepository.existsById(Id)) {
            throw new CategoryNotFoundException("Categoria con ID " + id + " non trovata per l'eliminazione.");
        }
        categoryRepository.deleteById(Id);
    }
}
