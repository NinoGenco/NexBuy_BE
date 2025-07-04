package org.polimi.nexbuy.service.impl.products;

import org.polimi.nexbuy.exception.products.CategoryAlreadyExistsException;
import org.polimi.nexbuy.exception.products.CategoryNotFoundException;
import org.polimi.nexbuy.model.products.SubCategory;
import org.polimi.nexbuy.repository.products.CategoryRepository;
import org.polimi.nexbuy.repository.products.SubCategoryRepository;
import org.polimi.nexbuy.service.def.products.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    public Optional<SubCategory> getSubCategoryById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId);
    }

    public SubCategory addSubCategory(SubCategory subCategory) {
        if (subCategoryRepository.existsById(subCategory.getId())) {
            throw new CategoryAlreadyExistsException("Sottocategoria Id" + subCategory.getId() + " esiste già");
        }

        if (!categoryRepository.existsById(subCategory.getCategory().getId())){
            throw new CategoryNotFoundException("Categoria con Id" + subCategory.getCategory().getId() + " non trovata");
        }
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory updateSubCategory(SubCategory subCategory) {
        if (!subCategoryRepository.existsById(subCategory.getId())) {
            throw new CategoryNotFoundException("Sottocategoria con Id" + subCategory.getId() + " non trovata per l'aggiornamento");
        }

        if (!categoryRepository.existsById(subCategory.getCategory().getId())) {
            throw new CategoryNotFoundException("Categoria con Id" + subCategory.getCategory().getId() + " non trovata");
        }
        return subCategoryRepository.save(subCategory);
    }

    public void deleteSubCategory(Long subCategoryId) {
        if (!subCategoryRepository.existsById(subCategoryId)) {
            throw new CategoryNotFoundException("Sottocategoria con Id" + subCategoryId + " non trovata per l'eliminazione");
        }
        subCategoryRepository.deleteById(subCategoryId);
    }
}
