package com.arquitectura.proyecto.business;

import com.arquitectura.proyecto.model.DeletePetRequest;
import com.arquitectura.proyecto.model.HomeResponse;
import com.arquitectura.proyecto.model.Pet;
import com.arquitectura.proyecto.repository.PetRepository;
import com.arquitectura.proyecto.repository.PetRequestRepository;
import com.arquitectura.proyecto.repository.UserRepository;
import com.arquitectura.proyecto.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Clase que implementa Logica de Mascotas
 * @Author Steven Newton
 */
@Slf4j
@Service
public class PetService{
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private PetRequestRepository petRequestRepository;
    @Autowired
    private UserRepository userRepository;

    public HomeResponse home(String token){
        log.debug("home service");
        try {
            String userID = userRepository.findIdByEmail(JwtUtils.getUsernameFromToken(token));
            HomeResponse homeResponse = new HomeResponse();
            homeResponse.setPetlist(petRepository.findPetsByOwner(userID));
            homeResponse.setPetRequests(petRequestRepository.findAllByUserID(userID));
            return homeResponse;
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("error consulting home");
        }
    }

    public List<Pet> getPets(String token){
        String userID = userRepository.findIdByEmail(JwtUtils.getUsernameFromToken(token));
        return petRepository.findAllByOwnerNot(userID);
    }
    public void addPet( Pet pet, String token) {
        String userID = userRepository.findIdByEmail(JwtUtils.getUsernameFromToken(token));
        pet.setOwnerId(userID);
        petRepository.save(pet);
    }

    public HttpStatus deletePet(DeletePetRequest request, String token) {
        String userID = userRepository.findIdByEmail(JwtUtils.getUsernameFromToken(token));
        Optional<Pet> pet = petRepository.findById(request.getPetID());
        if (pet.isPresent()){
            if (pet.get().getOwnerId().equals(userID)){
                petRepository.delete(pet.get());
                petRequestRepository.deleteByPetId(request.getPetID());
                return HttpStatus.OK;
            }else {
                throw  new RuntimeException("only pet owner can do that");
            }
        }else {
            throw new RuntimeException("pet not found");
        }
    }
}
