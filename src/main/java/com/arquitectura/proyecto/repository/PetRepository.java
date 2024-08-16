package com.arquitectura.proyecto.repository;

import com.arquitectura.proyecto.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PetRepository extends MongoRepository<Pet,String> {
    @Query("{ 'ownerId': ?0 }")
    List<Pet> findPetsByOwner(String ownerId);
    @Query("{ 'ownerId' : { $ne: ?0 } }")
    List<Pet> findAllByOwnerNot(String ownerId);
}
