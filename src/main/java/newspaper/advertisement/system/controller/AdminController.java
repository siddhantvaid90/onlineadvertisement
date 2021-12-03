package newspaper.advertisement.system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.AppUser;
import newspaper.advertisement.system.service.AdminService;
import newspaper.advertisement.system.service.AppUserService;



/**
 * 
 * controller for Admin functionalities
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService service;

	@Autowired
	AppUserService appUserService;

//  View Details of User
	@GetMapping("/admin/getAllUserDetails")
	public ResponseEntity<?> getAllUserDetails() throws DataNotFoundException {
		LOG.info("ViewUsers");
			List<AppUser> list =  service.getAllUsers();
			return new ResponseEntity<List>(list,HttpStatus.FOUND);
		
	}

//	View list of Users
	@GetMapping("/admin/getUserList")
	public ResponseEntity<?> getUserList() throws DataNotFoundException {
		LOG.info("ViewUserList");
			List<String> list = service.getUsersList();
			return new ResponseEntity<List>(list,HttpStatus.FOUND);
	}

//	Delete User
	@DeleteMapping("/admin/deleteUser/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable int userid) throws DataNotFoundException {
		LOG.info("deleteUser-controller");
			service.deleteUser(userid);
			return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
	}

}