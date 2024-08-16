package com.arquitectura.proyecto.business;

import com.arquitectura.proyecto.model.ContactRequest;
import com.arquitectura.proyecto.model.PetRequest;
import com.arquitectura.proyecto.model.PetRequestRequest;
import com.arquitectura.proyecto.model.User;
import com.arquitectura.proyecto.repository.PetRequestRepository;
import com.arquitectura.proyecto.repository.UserRepository;
import com.arquitectura.proyecto.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class PetRequestService {
    @Autowired
    private PetRequestRepository petRequestRepository;
    @Autowired
    private UserRepository userRepository;

    public void addRequest(PetRequestRequest request, String token) {
        User user = userRepository.findByEmail(JwtUtils.getUsernameFromToken(token));
        PetRequest petRequest = new PetRequest();
        petRequest.setLocation(user.getLocation());
        petRequest.setEmail(user.getEmail());
        petRequest.setFirstName(user.getFirstName());
        petRequest.setLastName(user.getLastName());
        petRequest.setPetID(request.getPetID());
        petRequest.setMessage(request.getMessage());
        petRequest.setContacted(false);
        petRequestRepository.save(petRequest);
    }

    public void contact(ContactRequest request) {
        petRequestRepository.findById(request.getRequestID()).get().setContacted(true);
    }
}
