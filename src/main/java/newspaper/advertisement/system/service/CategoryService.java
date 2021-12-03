package newspaper.advertisement.system.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import newspaper.advertisement.system.exception.CategoryNotFoundException;
import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.Category;
import newspaper.advertisement.system.repository.CategoryRepository;

/**
 * 
 * service for Category functionalities
 *
 */

@Service
public class CategoryService {
	private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

	@Autowired
	private CategoryRepository repository;

	// Add category
	public Category addCategory(Category cat) {
		LOG.info("addCategory");
		return repository.save(cat);

	}

//	View Category
	public List<Category> getAllCategory() {
		LOG.info("ViewCategory");
		List<Category> list = repository.findAll();
		if(list.isEmpty())
			throw new DataNotFoundException("Data not found");
		else
			return list;
	}

//	Delete category by id
	public void deleteCategory(int catid) {
		LOG.info("deleteCategory");
		repository.deleteById(catid);
	}

//	 Method that works with custom exception
	public Category findCategoryBycatid(int catid) {
		LOG.info("findCategoryById");
		Optional<Category> optCat = repository.findById(catid);
		if (optCat.isPresent())
			return optCat.get();
		else
			throw new CategoryNotFoundException("Category not found");
			
	}

}