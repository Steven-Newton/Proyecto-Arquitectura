package com.arquitectura.proyecto.business;

import com.arquitectura.proyecto.model.User;
import com.arquitectura.proyecto.model.UserRequest;
import com.arquitectura.proyecto.repository.UserRepository;
import com.arquitectura.proyecto.util.AESEncryptionDecryption;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * service for user collection
 * @Author Steven Newton
 */

@Slf4j
@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;

    private static final String key = "meowsers";
    public HttpStatus addUser (UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("user already exists");
        }else {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setCellphone(request.getCellphone());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setLocation(request.getLocation());
            user.setPassword(AESEncryptionDecryption.encrypt(request.getPassword(),key));
            user.setCreatedAt(LocalDate.now());
            userRepository.save(user);
        }
        return  HttpStatus.OK;
    }

    public boolean validaUsuario(String email, String password) {
    return userRepository.validateUser(email,AESEncryptionDecryption.encrypt(password,key));
    }

}
