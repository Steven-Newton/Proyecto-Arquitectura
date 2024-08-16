package com.arquitectura.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Request Body for adding User
 * @Author Steven Newton
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NonNull
    private String email;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String cellphone;
    @NonNull
    private String password;
    @NonNull
    private String location;
}
