package org.polimi.nexbuy.repository.products;

import org.polimi.nexbuy.model.products.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
