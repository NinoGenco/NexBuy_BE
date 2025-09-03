package org.polimi.nexbuy.service;

import org.polimi.nexbuy.dto.output.category.CategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.model.Category;

import java.util.List;

public interface SubCategoryService {

    void createSubCategory(Category category) throws DataAccessServiceException;

    boolean updateSubCategory(Category category, String name) throws DataAccessServiceException, IllegalAccessException;

    void deleteSubCategory(String name) throws DataAccessServiceException;

    CategorySummaryDTO getSubCategoryByName(String name) throws DataAccessServiceException, IllegalAccessException;

    List<CategorySummaryDTO> getAllSubCategories() throws DataAccessServiceException, IllegalAccessException;

}
