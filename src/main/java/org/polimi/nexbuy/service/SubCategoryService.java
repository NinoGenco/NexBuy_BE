package org.polimi.nexbuy.service;
;
import org.polimi.nexbuy.dto.output.sub_category.SubCategorySummaryDTO;
import org.polimi.nexbuy.exception.DataAccessServiceException;
import org.polimi.nexbuy.model.SubCategory;

import java.util.List;

public interface SubCategoryService {

    void createSubCategory(SubCategory subCategory, String categoryName) throws DataAccessServiceException;

    boolean updateSubCategory(SubCategory subCategory, String subCategoryName, String categoryName) throws DataAccessServiceException, IllegalAccessException;

    void deleteSubCategory(String name) throws DataAccessServiceException;

    SubCategorySummaryDTO getSubCategoryByName(String name) throws DataAccessServiceException, IllegalAccessException;

    List<SubCategorySummaryDTO> getAllSubCategories() throws DataAccessServiceException, IllegalAccessException;

}
