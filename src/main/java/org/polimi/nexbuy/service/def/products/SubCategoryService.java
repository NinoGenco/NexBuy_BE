package org.polimi.nexbuy.service.def.products;

import org.polimi.nexbuy.model.products.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {

    List<SubCategory> getAllSubCategories();
    Optional<SubCategory> getSubCategoryById(Long subCategoryId);
    SubCategory addSubCategory(SubCategory subCategory);
    SubCategory updateSubCategory(SubCategory subCategory);
    void deleteSubCategory(Long subCategoryId);
}
