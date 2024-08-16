package com.arquitectura.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String cellphone;
    private String password;
    private String location;
    private LocalDate createdAt;
}
