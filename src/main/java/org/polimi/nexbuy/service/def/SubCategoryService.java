package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.model.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {

    List<SubCategory> getAllSubCategories();
    Optional<SubCategory> getSubCategoryById(Long subCategoryId);
    SubCategory addSubCategory(SubCategory subCategory);
    SubCategory updateSubCategory(SubCategory subCategory);
    void deleteSubCategory(Long subCategoryId);
}
