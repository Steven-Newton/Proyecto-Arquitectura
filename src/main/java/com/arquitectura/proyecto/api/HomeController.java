package com.arquitectura.proyecto.api;

import com.arquitectura.proyecto.business.PetService;
import com.arquitectura.proyecto.constants.Constants;
import com.arquitectura.proyecto.model.HomeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/private")
public class HomeController {
    @Autowired
    private PetService petService;

    @Operation(description = "returns users active pets and requests",summary = "home")
    @ApiResponse(responseCode = "200", description = "home")
    @Parameter(required = true, in = ParameterIn.HEADER, name = Constants.AUTHORIZATION_HEADER, example = "Bearer ey,,,,",schema = @Schema(type = "string"))
    @GetMapping("home")
    public HomeResponse home(@RequestHeader("Authorization") String token){
        log.debug("home controller");
        return petService.home(token);
    }
}
