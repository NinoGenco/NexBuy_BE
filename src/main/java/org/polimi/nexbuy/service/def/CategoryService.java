package org.polimi.nexbuy.service.def;

import org.polimi.nexbuy.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> getAllCategories();
    Optional<Category> getCategoryById(long categoryId);
    void deleteCategory(long categoryId);
    Category updateCategory(Category category);
    Category addCategory(Category category);
}
