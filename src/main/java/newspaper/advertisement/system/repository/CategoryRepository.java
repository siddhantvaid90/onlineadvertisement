package newspaper.advertisement.system.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import newspaper.advertisement.system.model.Category;

/**
 * 
 * repository for Category functionalities
 *
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.catname = :catname")
	public Optional<Category> findBycatName(@Param("catname")String catname);

}
