package newspaper.advertisement.system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import newspaper.advertisement.system.exception.*;
import newspaper.advertisement.system.jwt.JwtTokenUtil;
import newspaper.advertisement.system.jwt.TokenEntity;
import newspaper.advertisement.system.model.AppUser;
import newspaper.advertisement.system.model.Login;
import newspaper.advertisement.system.repository.AppUserRepository;
import newspaper.advertisement.system.repository.LoginRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("authorization")
public class AuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	AppUserRepository userRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	AppUser user;
	
	Login admin;
	
	@PostMapping(value="login/user")
	public ResponseEntity<?> createUserAuthenticationToken(@RequestBody Login person) throws Exception {

		Optional<AppUser> userData = userRepository.findByEmail(person.getEmail());

		if (userData.isPresent()) {
			user = userData.get();
		} else {
			throw new DataNotFoundException("User not found with username: " + person.getEmail());
		}

		if (!(user.getPassword().equals(person.getPassword())))
			throw new DataNotFoundException("Invalid Password");

		String token = jwtTokenUtil.generateToken(user);

		return ResponseEntity.ok(new TokenEntity(token));
		
		//return new ResponseEntity<String>(token, HttpStatus.OK);
	}
	
	@PostMapping(value="login/admin")
	public ResponseEntity<?> createAdminAuthenticationToken(@RequestBody  Login person) throws Exception {

		Optional<Login> userData = loginRepository.findById(person.getEmail());

		if (userData.isPresent()) {
			 admin = userData.get();
		} else {
			throw new DataNotFoundException("User not found with username: " + person.getEmail());
		}

		if (!(admin.getPassword().equals(person.getPassword())))
			throw new DataNotFoundException("Invalid Password");

		String token = jwtTokenUtil.generateAdminToken(admin);

		return ResponseEntity.ok(new TokenEntity(token));
		
		//return new ResponseEntity<String>(token, HttpStatus.OK);
	}
}
