package org.polimi.nexbuy.repository;

import jakarta.transaction.Transactional;
import org.polimi.nexbuy.model.Token;
import org.polimi.nexbuy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository per la gestione dei token
 */
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Token t WHERE t.user = :user")
    boolean existsByUser(@Param("user") User user);

    @Query("SELECT t.token FROM Token t WHERE t.user = :user")
    String findTokenByUser(@Param("user") User user);

    @Transactional
    void deleteByToken(String token);

}
