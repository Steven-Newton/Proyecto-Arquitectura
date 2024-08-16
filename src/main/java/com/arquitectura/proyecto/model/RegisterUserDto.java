package com.arquitectura.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class
RegisterUserDto {
    private String fullName;
    private String email;
    private String password;
}
