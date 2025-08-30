package org.polimi.nexbuy.repository;

import jakarta.transaction.Transactional;
import org.polimi.nexbuy.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Image i WHERE i.id IN :imageIds")
    void deleteAllById(@Param("imageIds") List<Long> imageIds);

}
