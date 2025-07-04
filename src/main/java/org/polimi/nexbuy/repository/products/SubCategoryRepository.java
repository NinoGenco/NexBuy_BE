package org.polimi.nexbuy.repository.products;

import org.polimi.nexbuy.model.products.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    SubCategory findByName(String name);
}
