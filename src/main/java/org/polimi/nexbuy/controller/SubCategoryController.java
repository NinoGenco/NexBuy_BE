package org.polimi.nexbuy.controller;

import lombok.AllArgsConstructor;
import org.polimi.nexbuy.dto.input.InputDTO;
import org.polimi.nexbuy.dto.input.sub_category.SubCategoryDTO;
import org.polimi.nexbuy.dto.input.sub_category.UpdateSubCategoryDTO;
import org.polimi.nexbuy.dto.mapper.sub_category.SubCategoryMapper;
import org.polimi.nexbuy.dto.mapper.sub_category.UpdateSubCategoryMapper;
import org.polimi.nexbuy.dto.output.sub_category.SubCategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.response.ResponseMessage;
import org.polimi.nexbuy.service.SubCategoryService;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/sub-category")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    private final SubCategoryMapper subCategoryMapper;
    private final UpdateSubCategoryMapper updateSubCategoryMapper;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> createSubCategory(
            @RequestBody InputDTO subCategoryDTO
    ) throws DataAccessServiceException {
        try {
            if (subCategoryDTO instanceof SubCategoryDTO) {
                subCategoryService.createSubCategory(subCategoryMapper.subCategoryDTOToSubCategory(subCategoryDTO), ((SubCategoryDTO) subCategoryDTO).getCategoryName());
                return new ResponseEntity<>(new ResponseMessage("Sotto-categoria registrata"), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException("Sotto-categoria non registrata - CustomType non valido");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/update/{name}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseMessage> updateSubCategory(
            @PathVariable String name,
            @RequestBody InputDTO subCategoryDTO
    ) throws DataAccessServiceException, IllegalAccessException {
        try {
            if (subCategoryDTO instanceof UpdateSubCategoryDTO) {
                boolean isUpdated = subCategoryService.updateSubCategory(
                        updateSubCategoryMapper.subCategoryDTOToSubCategory(subCategoryDTO),
                        name,
                        ((UpdateSubCategoryDTO) subCategoryDTO).getCategoryName()
                );
                if (isUpdated) {
                    return new ResponseEntity<>(new ResponseMessage("Sotto-categoria aggiornata"), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(new ResponseMessage("Nessun campo è stato aggiornato"), HttpStatus.OK);
                }
            } else {
                throw new IllegalArgumentException("Sotto-categoria non aggiornata - CustomType non valido");
            }
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Messaggio non leggibile");
        }
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteSubCategory(@PathVariable String name) throws DataAccessServiceException {
        subCategoryService.deleteSubCategory(name);
        return new ResponseEntity<>(new ResponseMessage("Sotto-categoria eliminata"), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<SubCategorySummaryDTO> getSubCategoryByName(@PathVariable String name) throws DataAccessServiceException, IllegalAccessException {
        SubCategorySummaryDTO subCategory = subCategoryService.getSubCategoryByName(name);
        return new ResponseEntity<>(subCategory, HttpStatus.OK);
    }

    @RequestMapping(value = "/sub-categories", method = RequestMethod.GET)
    public ResponseEntity<List<SubCategorySummaryDTO>> getAllSubCategories() throws DataAccessServiceException, IllegalAccessException {
        List<SubCategorySummaryDTO> subCategories = subCategoryService.getAllSubCategories();
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }

}
