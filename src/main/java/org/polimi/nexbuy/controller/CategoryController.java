package org.polimi.nexbuy.controller;

import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.category.CategoryDTO;
import org.polimi.nexbuy.dto.input.category.UpdateCategoryDTO;
import org.polimi.nexbuy.dto.mapper.category.CategoryMapper;
import org.polimi.nexbuy.dto.mapper.category.UpdateCategoryMapper;
import org.polimi.nexbuy.dto.output.category.CategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.response.ResponseMessage;
import org.polimi.nexbuy.service.CategoryService;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;
    private final UpdateCategoryMapper updateCategoryMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createCategory (
            @RequestBody InputDTO categoryDTO
    ) throws DataAccessServiceException {

        try {
            if (categoryDTO instanceof CategoryDTO) {
                categoryService.createCategory(categoryMapper.categoryDTOToCategory(categoryDTO));

                return new ResponseEntity<>(new ResponseMessage("Categoria registrata"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Categoria non registrata - CustomType non valido");
            }
        } catch (JsonParseException e){
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/update/{name}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateCategory (
            @PathVariable String name,
            @RequestBody InputDTO categoryDTO
    ) throws DataAccessServiceException, IllegalAccessException {
        try {
            if (categoryDTO instanceof UpdateCategoryDTO) {
                boolean isUpdated = categoryService.updateCategory(updateCategoryMapper.categoryDTOToCategory(categoryDTO), name);
                if (isUpdated) {
                    return new ResponseEntity<>(new ResponseMessage("Categoria aggiornato"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Nessun campo è stato aggiornato"), HttpStatus.OK);
                }
            } else {
                throw new IllegalArgumentException("Categoria non aggiornata - CustomType non valido");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteCategory(@PathVariable String name) throws DataAccessServiceException {
        categoryService.deleteCategory(name);
        return new ResponseEntity<>(new ResponseMessage("Categoria eliminata"), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<CategorySummaryDTO> getProductById(@PathVariable String name) throws DataAccessServiceException, IllegalAccessException {
        CategorySummaryDTO category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public ResponseEntity<List<CategorySummaryDTO>> getAllProducts() throws DataAccessServiceException, IllegalAccessException {
        List<CategorySummaryDTO> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
