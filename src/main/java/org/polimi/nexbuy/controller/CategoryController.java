package org.polimi.nexbuy.controller;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.CategoryRequest;
import org.polimi.nexbuy.DTO.response.CategoryResponse;
import org.polimi.nexbuy.exception.CategoryNotFoundException;
import org.polimi.nexbuy.model.Category;
import org.polimi.nexbuy.service.def.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Ecommerce/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //visualizzare tutte le categorie
    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    //visualizzare una categoria specifica per ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        CategoryResponse categoryResponse = new CategoryResponse();
        try {
            Optional<Category> category = categoryService.getCategoryById(id);
            categoryResponse.setId(category.get().getId());
            categoryResponse.setName(category.get().getName());
            categoryResponse.setDescription(category.get().getDescription());
        } catch (CategoryNotFoundException e) {
            categoryResponse.setId(id);
            categoryResponse.setName(e.getMessage());
            categoryResponse.setDescription("Category not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(categoryResponse);
        }

        return ResponseEntity.ok(categoryResponse);
    }

    //creare una nuova categoria
    @PostMapping("/add")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category createdCategory = categoryService.addCategory(category);

        CategoryResponse categoryResponseDTO = new CategoryResponse();
        categoryResponseDTO.setId(createdCategory.getId());
        categoryResponseDTO.setName(createdCategory.getName());
        categoryResponseDTO.setDescription(createdCategory.getDescription());

        return ResponseEntity.status(201).body(categoryResponseDTO);
    }

    //Modificare una categoria esistente
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setId(id);
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category updatedCategory = categoryService.updateCategory(category);

        CategoryResponse categoryResponseDTO = new CategoryResponse();
        categoryResponseDTO.setId(updatedCategory.getId());
        categoryResponseDTO.setName(updatedCategory.getName());
        categoryResponseDTO.setDescription(updatedCategory.getDescription());

        return ResponseEntity.ok(categoryResponseDTO);
    }

    //eliminare una categoria
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
