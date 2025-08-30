package org.polimi.nexbuy.repository;

import org.polimi.nexbuy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
