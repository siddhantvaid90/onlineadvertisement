package newspaper.advertisement.system.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import newspaper.advertisement.system.model.AppUser;

/**
 * 
 * repository for Admin functionalities
 *
 */

@Repository
public interface AdminRepository extends JpaRepository<AppUser, Integer> {

//	View user list
	@Modifying
	@Transactional
	@Query(value = "SELECT first_name,last_name FROM user_adv", nativeQuery = true)
	public abstract List<String> viewUserList();

//	Delete user by id
	@Modifying
	@Transactional
	@Query("DELETE from AppUser a WHERE a.userid = :userid")
	public abstract void deleteById(int userid);
}