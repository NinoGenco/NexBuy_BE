package org.polimi.nexbuy.repository;

import org.polimi.nexbuy.model.Category;
import org.polimi.nexbuy.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    boolean existsByNameAndCategory(String name, Category category);

    Optional<SubCategory> findByName(String name);

}
