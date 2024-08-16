package com.arquitectura.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("petRequest")
public class PetRequest {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String location;
    private String message;
    private String petID;
    private Boolean contacted;
    private String userID;
}