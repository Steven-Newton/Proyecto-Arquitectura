package com.arquitectura.proyecto.api;

import com.arquitectura.proyecto.business.PetService;
import com.arquitectura.proyecto.constants.Constants;
import com.arquitectura.proyecto.model.DeletePetRequest;
import com.arquitectura.proyecto.model.Pet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller for pet logic
 * @Author Steven Newton
 */

@RestController
@Slf4j
@RequestMapping("/api/private")
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(description = "gets pets",summary = "getPets")
    @ApiResponse(responseCode = "200", description = "getPets")
    @Parameter(required = true, in = ParameterIn.HEADER, name = Constants.AUTHORIZATION_HEADER, example = "Bearer ey,,,,",schema = @Schema(type = "string"))
    @GetMapping("/pets")
    public List<Pet> getPets(@RequestHeader("Authorization") String token){
        return  petService.getPets(token);
    }

    @DeleteMapping("/pet")
    public HttpStatus deletePet(@RequestBody DeletePetRequest request, @RequestHeader("Authorization")String token){
        return petService.deletePet(request,token);
    }


    @Operation(description = "adds pet",summary = "addPet")
    @ApiResponse(responseCode = "200", description = "addPet")
    @Parameter(required = true, in = ParameterIn.HEADER, name = Constants.AUTHORIZATION_HEADER, example = "Bearer ey,,,,",schema = @Schema(type = "string"))
    @PostMapping("/pet")
    public HttpStatus addPet(@RequestBody Pet pet, @RequestHeader("Authorization") String token){
        log.debug(String.format("%s",pet));
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            petService.addPet(pet,token);
            httpStatus = HttpStatus.OK;
        }catch (Exception e){
            log.debug(e.getMessage());
        }
    return  httpStatus;
    }

}
