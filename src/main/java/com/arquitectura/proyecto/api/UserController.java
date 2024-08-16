package com.arquitectura.proyecto.api;

import com.arquitectura.proyecto.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Steven Newton
 */
@RestController
	@RequestMapping(path="/api/user")
	public class UserController {
		@Autowired
		private  UserService userService;
}
