package com.arquitectura.proyecto.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HomeResponse {
    private List<Pet> petlist;
    private List<PetRequest> petRequests;
}
