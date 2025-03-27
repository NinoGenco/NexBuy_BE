package org.polimi.nexbuy.controller;

import lombok.RequiredArgsConstructor;
import org.polimi.nexbuy.DTO.request.SubCategoryRequest;
import org.polimi.nexbuy.DTO.response.SubCategoryResponse;
import org.polimi.nexbuy.exception.CategoryAlreadyExistsException;
import org.polimi.nexbuy.exception.CategoryNotFoundException;
import org.polimi.nexbuy.model.SubCategory;
import org.polimi.nexbuy.service.def.SubCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Ecommerce/subCategory")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    //creazione di una nuova sottocategoria
    @PostMapping("/add")
    public ResponseEntity<SubCategoryResponse> addSubCategory(@RequestBody SubCategoryRequest subCategoryRequest) {
        try {
            SubCategory subCategory = new SubCategory();
            subCategory.setName(subCategoryRequest.getName());

            //verifica la categoria associata
            subCategoryService.addSubCategory(subCategory);

            SubCategoryResponse response = new SubCategoryResponse();
            response.setId(subCategory.getId());
            response.setName(subCategory.getName());
            response.setCategoryName(subCategory.getCategory().getName());

            return ResponseEntity.status(201).body(response);  //status 201: Created

        } catch (CategoryAlreadyExistsException e) {
            //se la sottocategoria esiste già, restituisci una risposta con errore
            return ResponseEntity.status(400).body(new SubCategoryResponse("Sottocategoria già esistente: " + e.getMessage()));
        } catch (CategoryNotFoundException e) {
            // Se la categoria associata non esiste
            return ResponseEntity.status(404).body(new SubCategoryResponse("Categoria non trovata: " + e.getMessage()));
        }
    }

    //ottieni tutte le sottocategorie
    @GetMapping("/all")
    public ResponseEntity<List<SubCategoryResponse>> getAllSubCategories() {
        List<SubCategoryResponse> subCategoryResponses = new ArrayList<>();
        List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
        for (SubCategory subCategory : subCategories) {
            SubCategoryResponse response = new SubCategoryResponse();
            response.setId(subCategory.getId());
            response.setName(subCategory.getName());
            response.setCategoryName(subCategory.getCategory().getName());
            subCategoryResponses.add(response);
        }

        return ResponseEntity.ok(subCategoryResponses);
    }

    //ottieni una sottocategoria per ID
    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryResponse> getSubCategoryById(@PathVariable Long id) {
        Optional<SubCategory> subCategoryOptional = subCategoryService.getSubCategoryById(id);

        if (subCategoryOptional.isPresent()) {
            SubCategory subCategory = subCategoryOptional.get();
            SubCategoryResponse response = new SubCategoryResponse();
            response.setId(subCategory.getId());
            response.setName(subCategory.getName());
            response.setCategoryName(subCategory.getCategory().getName());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(new SubCategoryResponse("Sottocategoria con ID " + id + " non trovata"));
        }
    }

    //aggiorna una sottocategoria esistente
    @PutMapping("/update/{id}")
    public ResponseEntity<SubCategoryResponse> updateSubCategory(@PathVariable Long id, @RequestBody SubCategoryRequest subCategoryRequest) {
        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);  //imposto l'ID per l'aggiornamento
        subCategory.setName(subCategoryRequest.getName());

        try {
            SubCategory updatedSubCategory = subCategoryService.updateSubCategory(subCategory);

            SubCategoryResponse response = new SubCategoryResponse();
            response.setId(updatedSubCategory.getId());
            response.setName(updatedSubCategory.getName());
            response.setCategoryName(updatedSubCategory.getCategory().getName());

            return ResponseEntity.ok(response);

        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(404).body(new SubCategoryResponse("Sottocategoria non trovata per l'aggiornamento"));
        }
    }

    //eliminazione di una sottocategoria
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        try {
            subCategoryService.deleteSubCategory(id);
            return ResponseEntity.noContent().build();  //status 204: No Content
        } catch (CategoryNotFoundException e) {
            return ResponseEntity.status(404).build();  //status 404: Not Found
        }
    }
}
