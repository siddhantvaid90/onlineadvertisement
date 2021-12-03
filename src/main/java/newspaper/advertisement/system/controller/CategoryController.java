package newspaper.advertisement.system.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.Category;
import newspaper.advertisement.system.service.AppUserService;
import newspaper.advertisement.system.service.CategoryService;

/**
 * 
 * controller for Category functionalities
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService service;

	@Autowired
	AppUserService appUserService;

// Admin can add category
	@PostMapping("/admin/addCategory")
	public ResponseEntity<?> addCategory(@RequestBody Category cat) {
		LOG.info("addcategory");
		service.addCategory(cat);
		return new ResponseEntity<String>("Category added successfully",HttpStatus.CREATED);
	}

//	User can view category
	@GetMapping(value = "/user/getAllCategory")
	private ResponseEntity<?> getAllCat() {
		LOG.info("ViewCategory");
			List<Category> list = service.getAllCategory();
		return new ResponseEntity<>(list,HttpStatus.FOUND);
	}

//	Admin and user can view category by category id
	@GetMapping("/getCategory/{catid}")
	public ResponseEntity<Category> getCategoryBycatid(@PathVariable("catid") int catid) {
		LOG.info("getcat");
		Category cat = service.findCategoryBycatid(catid);
		HttpHeaders headers = new HttpHeaders();
		if (cat != null) {
			headers.add("Category name", cat.getCatname());
			return new ResponseEntity<Category>(cat, headers, HttpStatus.OK);
		} else {
			headers.add("Category name", "Category not available");
			return new ResponseEntity<Category>(cat, headers, HttpStatus.NOT_FOUND);
		}
	}

//	Admin can delete a category by category id
	@DeleteMapping("/admin/deleteCategory/{catid}")
	public ResponseEntity<?> deleteCat(@PathVariable int catid) {
		LOG.info("deleteCategory");
			service.deleteCategory(catid);
			return new ResponseEntity<String>("Category deleted successfully",HttpStatus.OK);
	}

}