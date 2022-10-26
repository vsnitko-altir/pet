package com.vsnitko.pet.repository.jpa;

import com.vsnitko.pet.model.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author v.snitko
 * @since 2022.10.15
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("update User set firstName = :firstName, lastName = :lastName where email = :email")
    int updateUser(@Param("email") String email, @Param("firstName") String firstName, @Param("lastName") String lastName);
}
