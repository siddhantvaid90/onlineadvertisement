package newspaper.advertisement.system.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.AppUser;
import newspaper.advertisement.system.model.Role;
import newspaper.advertisement.system.repository.AppUserRepository;


/**
 * 
 * 
 * Service for Registration, Login and Logout functionalities
 *
 */

@Service
public class AppUserService {

	private static final Logger LOG = LoggerFactory.getLogger(AppUserService.class);

	@Autowired
	private AppUserRepository repository;
	

	private AppUser currentAppUser; // access control to APIs

//	Registration  User
	public AppUser registerUser(AppUser appusers) {
		LOG.info("Registered Successfully");
		
		appusers.setRole(Role.USER);
		return repository.save(appusers);
		
	}

//Update User
	public void updateUser(AppUser user) {
		Optional<AppUser> appUser = repository.findByEmail(user.getEmail());
		if(appUser.isPresent()) {
			AppUser user1 =appUser.get();
			user.setUserid(user1.getUserid());
			  repository.save(user);
		}
		else
			throw new DataNotFoundException("Data not found");
	}
// logout
	public String logout() {
		LOG.info("logoutService");
		currentAppUser = null;
		return " Logged out successfully";
	}

// login status
	public AppUser loginStatus() {
		LOG.info("loginStatusService");
		return currentAppUser;
	}

}