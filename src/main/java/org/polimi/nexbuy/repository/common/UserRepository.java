package org.polimi.nexbuy.repository.common;

import org.polimi.nexbuy.DTO.response.common.UserResponse;
import org.polimi.nexbuy.model.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findAllByOrderByLastNameAsc();

    boolean existsByEmail(String email);

    @Query("SELECT new org.polimi.nexbuy.DTO.response.UserResponse (u.id, u.email, u.firstName, u.lastName, u.dateOfBirth, u.telephone, u.role)"+
    "FROM User u ORDER BY u.lastName ASC")
    List<UserResponse> findAllUsersOrderByLastNameAsc();
}
