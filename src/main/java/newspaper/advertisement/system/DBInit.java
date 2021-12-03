package newspaper.advertisement.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import newspaper.advertisement.system.model.AppUser;
import newspaper.advertisement.system.model.Category;
import newspaper.advertisement.system.model.Login;
import newspaper.advertisement.system.model.Role;
import newspaper.advertisement.system.repository.AdvertiseRepository;
import newspaper.advertisement.system.repository.AppUserRepository;
import newspaper.advertisement.system.repository.CategoryRepository;
import newspaper.advertisement.system.repository.LoginRepository;

@Component
public class DBInit implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(DBInit.class);
	@Autowired
	AdvertiseRepository advertiseRepository;
	
	@Autowired
	AppUserRepository appUserRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		logger.info("Admin details created in DB");
		
		Login admin = new Login("chinnusatwika@gmail.com","Satwika123",Role.ADMIN);
		loginRepository.save(admin);
		Login admin1 = new Login("Poojadeshmukh@gmail.com", "Pooja123",Role.ADMIN);
		loginRepository.save(admin1);
		Category category = new Category("Matrimony","Search for soulmate");
		categoryRepository.save(category);
		Category category1 = new Category("Recruitment"," hire your employee.");
		categoryRepository.save(category1);
		
	}
	

}
