package newspaper.advertisement.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import newspaper.advertisement.system.model.AppUser;

/**
 * 
 * repository for AppUser functionalities
 *
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

//	public AppUser findByUsername(String username);

	@Query("select a from AppUser a where a.email = :email")
	public Optional<AppUser> findByEmail(@Param("email")String email);

}