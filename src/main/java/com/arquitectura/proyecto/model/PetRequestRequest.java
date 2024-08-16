package com.arquitectura.proyecto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class PetRequestRequest {
    private String message;
    private String petID;
}
