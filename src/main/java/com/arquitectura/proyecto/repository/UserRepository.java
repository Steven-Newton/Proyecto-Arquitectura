package com.arquitectura.proyecto.repository;

import com.arquitectura.proyecto.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for user collection
 * @Author Steven Newton
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{ 'email': ?0 }",exists = true)
    boolean existsByEmail(String email);
    @Query(value = "{ 'email': ?0,'password': ?1 }",exists = true)
    boolean validateUser(String email, String password);
    @Query(value = "{ 'email' : ?0 }")
    User findByEmail(String email);
    @Query(value = "{ 'email' : ?0 }", fields = "{ 'id' : 1 }")
    String findIdByEmail(String email);
}

