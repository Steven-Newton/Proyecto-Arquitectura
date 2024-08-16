package com.arquitectura.proyecto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("pet")
public class Pet {
    @Id
    private String id;
    private String name;
    private String imgUrl;
    private String description;
    private Integer age;
    private String type;
    private String location;
    private String ownerId;
}
