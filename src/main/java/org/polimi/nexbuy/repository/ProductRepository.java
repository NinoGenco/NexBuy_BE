package org.polimi.nexbuy.repository;

import org.polimi.nexbuy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select distinct p from Product p left join fetch p.images")
    List<Product> findAllWithImages();

    @Query("select p from Product p left join fetch p.images where p.id = :id")
    Optional<Product> findByIdWithImages(Long id);

}
