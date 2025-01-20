package com.example.user.repository;

import com.example.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    // JPQL or native query to find by username OR email
    @Query("SELECT u FROM UserEntity u WHERE u.username = :identifier OR u.email = :identifier")
    UserEntity findByEmailOrUsername(String identifier);
}
