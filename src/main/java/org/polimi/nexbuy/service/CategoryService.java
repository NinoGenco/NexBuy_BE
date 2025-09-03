package org.polimi.nexbuy.service;

import org.polimi.nexbuy.dto.output.category.CategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.model.Category;

import java.util.List;

public interface CategoryService {

    void createCategory(Category category) throws DataAccessServiceException;

    boolean updateCategory(Category category, String name) throws DataAccessServiceException, IllegalAccessException;

    void deleteCategory(String name) throws DataAccessServiceException;

    CategorySummaryDTO getCategoryByName(String name) throws DataAccessServiceException, IllegalAccessException;

    List<CategorySummaryDTO> getAllCategories() throws DataAccessServiceException, IllegalAccessException;
}