package com.arquitectura.proyecto.api;

import com.arquitectura.proyecto.business.PetRequestService;
import com.arquitectura.proyecto.constants.Constants;
import com.arquitectura.proyecto.model.ContactRequest;
import com.arquitectura.proyecto.model.Pet;
import com.arquitectura.proyecto.model.PetRequest;
import com.arquitectura.proyecto.model.PetRequestRequest;
import com.arquitectura.proyecto.repository.PetRequestRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * controller for Request Logic
 * @Author Steven Newton
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/private")
public class RequestController {
    @Autowired
    private PetRequestService petRequestService;

    @Operation(description = "adds petRequest",summary = "petRequest")
    @ApiResponse(responseCode = "200", description = "petRequest")
    @Parameter(required = true, in = ParameterIn.HEADER, name = Constants.AUTHORIZATION_HEADER, example = "Bearer ey,,,,",schema = @Schema(type = "string"))
    @PostMapping("/request")
    public HttpStatus petRequest(@RequestBody PetRequestRequest request, @RequestHeader("Authorization") String token){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            petRequestService.addRequest(request,token);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return  httpStatus;
    }

    @Operation(description = "marks request as contacted",summary = "contacted")
    @ApiResponse(responseCode = "200", description = "contacted")
    @Parameter(required = true, in = ParameterIn.HEADER, name = Constants.AUTHORIZATION_HEADER, example = "Bearer ey,,,,",schema = @Schema(type = "string"))
    @PostMapping("/request/contact")
    public HttpStatus contacted(@RequestBody ContactRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        try {
            petRequestService.contact(request);
        }catch (Exception e){
            log.debug(e.getMessage());
        }
        return  httpStatus;
    }


}
