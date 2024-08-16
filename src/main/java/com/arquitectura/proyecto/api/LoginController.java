package com.arquitectura.proyecto.api;

import com.arquitectura.proyecto.business.UserService;
import com.arquitectura.proyecto.config.JwtFilter;
import com.arquitectura.proyecto.model.*;
import com.arquitectura.proyecto.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;
import java.util.Date;

/**
 * login controller
 * @author Steven
 */
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class LoginController {
	@Autowired
	private  UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Operation(description = "allows user to log in",summary = "login")
	@ApiResponse(responseCode = "200", description = "login")
	@PostMapping("/login")
	public LoginResponse login (@RequestBody(required = true)LoginUserDto loginUserDto) throws ServletException {
		if(userService.validaUsuario(loginUserDto.getEmail(),loginUserDto.getPassword())) {
			LoginResponse loginResponse = new LoginResponse();
			loginResponse.setAccessToken(generateToken(loginUserDto.getEmail()));
			User user = userRepository.findByEmail(loginUserDto.getEmail());
			loginResponse.setCellphone(user.getCellphone());
			loginResponse.setLocation(user.getLocation());
			loginResponse.setFirstName(user.getFirstName());
			loginResponse.setLocation(user.getLocation());
			loginResponse.setEmail(loginResponse.getEmail());
			return loginResponse;
		}
		throw new RuntimeException("Nombre de usuario o contraseña incorrectos");
	}
	private String generateToken(String email) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 10);
		return Jwts.builder().setSubject(email).claim("role", "user")
				.setIssuedAt(new Date()).setExpiration(calendar.getTime())
				.signWith(SignatureAlgorithm.HS256, JwtFilter.secret.getBytes()).compact();
	}
	@Operation(description = "allows user to signup",summary = "signup")
	@ApiResponse(responseCode = "200", description = "login")
	@PostMapping("signup")
	public HttpStatus addUser (@RequestBody @Validated UserRequest user) {
		return userService.addUser(user);
	}
}