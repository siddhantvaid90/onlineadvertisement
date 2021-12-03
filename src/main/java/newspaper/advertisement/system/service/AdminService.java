package newspaper.advertisement.system.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.AppUser;
import newspaper.advertisement.system.repository.AdminRepository;

/**
 * 
 * service for Admin functionalities
 *
 */
@Service
public class AdminService {

	private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private AdminRepository repository;

//	Edit user
	public AppUser editUserProfile(AppUser edit) {
		LOG.info("UpdateUser");

		return repository.save(edit);

	}

//	View user details
	public List<AppUser> getAllUsers() {
		LOG.info("ViewAllUsers");
		List<AppUser>list = repository.findAll();
		if(list.isEmpty())
			throw new DataNotFoundException("data not found");
		return repository.findAll();
	}

//	Delete User
	public void deleteUser(int userid) {
		LOG.info("deleteUser-service");
		if(repository.existsById(userid)) {
		repository.deleteById(userid);
		}
		else
			throw new DataNotFoundException("data not found");
	}

//	View user list
	public List<String> getUsersList() {
		LOG.info("viewUserList");
		List<String> list= repository.viewUserList();
		if(list.isEmpty())
			throw new DataNotFoundException("data not found");
		return list;
	}

}