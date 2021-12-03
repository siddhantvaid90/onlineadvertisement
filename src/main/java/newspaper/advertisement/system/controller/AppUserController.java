package newspaper.advertisement.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.SignatureException;
import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.exception.InvalidTokenException;
import newspaper.advertisement.system.jwt.JwtTokenUtil;
import newspaper.advertisement.system.model.AppUser;

import newspaper.advertisement.system.service.AppUserService;

/**
 * 
 * 
 * Controller for Registration, Login and Logout functionalities
 *
 */

@RestController()
@CrossOrigin(origins = "http://localhost:4200")

public class AppUserController {

	private static final Logger LOG = LoggerFactory.getLogger(AppUserController.class);
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AppUserService userService;

//	Registration for Admin and User
	@PostMapping(value = "registration")
	public ResponseEntity<String> registerUser(@RequestBody AppUser users) {
		userService.registerUser(users);
		return new ResponseEntity<String>("User registered successfully",HttpStatus.CREATED);
		
	}

	// Update User details
	@PutMapping(value = "update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody AppUser user, HttpServletRequest request)throws DataNotFoundException {
		user= jwtTokenUtil.validateTokenAndGetUserDetails(request);
		userService.updateUser(user);
		return new ResponseEntity<>("User details updated",HttpStatus.OK);
		
	}
// Logout
	@GetMapping("/logout")
	public String logout() {
		LOG.info("logout");
		return userService.logout();
	}

	public void validateTokenAndGetUserDetails(HttpServletRequest request) {
		final String tokenHeader = request.getHeader("Authorization");

		String token = null;

		if (tokenHeader == null)
			throw new InvalidTokenException("User Not Logged In or token not included");
		// JWT Token is in the form "Bearer token". Remove Bearer word
		if (!tokenHeader.startsWith("Bearer "))
			throw new InvalidTokenException("Invalid Token");

		token = tokenHeader.substring(7);
		try {
			if (!(jwtTokenUtil.validateToken(token)))
				throw new InvalidTokenException("Token Expired. Need Relogin");

		} catch (SignatureException ex) {
			throw new InvalidTokenException("Invalid Token");
		}
	}
}