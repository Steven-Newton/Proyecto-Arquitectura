package com.arquitectura.proyecto.repository;

import com.arquitectura.proyecto.model.PetRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface PetRequestRepository extends MongoRepository<PetRequest,String> {
    @Query("{ 'petID': ?0, 'contacted' : false}")
    List<PetRequest> findAllByPetID(String petID);
    @Query("{ 'userID': ?0, 'contacted' : false}")
    List<PetRequest> findAllByUserID(String userID);
    void deleteByPetId(String petID);
}
