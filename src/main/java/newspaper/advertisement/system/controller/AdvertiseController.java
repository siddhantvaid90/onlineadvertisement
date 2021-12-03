package newspaper.advertisement.system.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import newspaper.advertisement.system.exception.AdvertiseNotFoundException;
import newspaper.advertisement.system.exception.CategoryNotFoundException;
import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.Advertise;
import newspaper.advertisement.system.service.AdvertiseService;
import newspaper.advertisement.system.service.AppUserService;

/**
 * 
 * controller for Advertise functionalities
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdvertiseController {

	private static final Logger LOG = LoggerFactory.getLogger(AdvertiseController.class);

	@Autowired
	private AdvertiseService service;

	@Autowired
	AppUserService appUserService;

//	To post advertise
	@PostMapping("/user/seller/AddNewAdv")
	public ResponseEntity<?> addNewAdvertise(@RequestBody Advertise adv) throws CategoryNotFoundException{
		LOG.info("addproduct");
			 service.addAdvertise( adv);
			 return new ResponseEntity<String>("Advertisement created successfully",HttpStatus.CREATED);
	}

	@PutMapping("/user/seller/editAdv")
	public ResponseEntity<?> updateAdvertise( @RequestBody Advertise adv  ) throws AdvertiseNotFoundException {
		LOG.info("updateproduct");
			 service.updateAdvertise(adv);
			 return new ResponseEntity<String>("Advertisement updated successfully",HttpStatus.OK);
	}

//	Admin can view all the advertises posted by the seller for changing the status 
	@GetMapping("/admin/getAllAdv")
	private ResponseEntity<List> getAllAdvA() throws DataNotFoundException {
		LOG.info("ViewAdvertises");
		List<Advertise>list = service.getAllAdvertises();
		if(list.isEmpty())
			throw new DataNotFoundException("Data not found,list is empty");
		return new ResponseEntity<List>(list,HttpStatus.FOUND);
	}

//	Admin can update the status posted by the seller
	@PutMapping("/admin/updateStatus/{advid}/{status}")
	public ResponseEntity<?> updateStatus(@PathVariable("advid") int advid,@PathVariable("status") String status) throws AdvertiseNotFoundException {
//	public void updateStatus(@PathVariable("advid") int advid, String status) {
//		LOG.info("updateStatus");
			service.updateAdvStatus(advid, status);
			return new ResponseEntity<String>("Status changes successfully",HttpStatus.OK);
	}

//	Buyer can view all the approved advertises posted by the seller
	@GetMapping("/user/buyer/getAllApprovedAdv")
	private ResponseEntity<?> getApprovedAdv() throws DataNotFoundException {
		LOG.info("ViewAdvertises");

		List<Advertise> list = service.getApprovedAdvs();
		if(list.isEmpty()) {
			throw new DataNotFoundException("No Approved advetisements");
		}
		return new ResponseEntity<List>(list,HttpStatus.FOUND);
	}

//	user can also search for advertises by advertisetitle
	@GetMapping("/user/getAdvertise/{advertisetitle}")
	public ResponseEntity<?> getAdvertiseByadvertisetitle(@PathVariable("advertisetitle")String advertisetitle) {
		LOG.info("getadvbytitle");
	
	List<Advertise> list =service.findAdvertiseByadvertisetitle(advertisetitle);
	if(list.isEmpty()) {
		throw new DataNotFoundException("No such advertisement is found");
	}
	return new ResponseEntity<List>(list,HttpStatus.FOUND);
		
		
	}

// seller can view advertise 
	@GetMapping("/user/seller/getAllAdv")
	private ResponseEntity<?> getAllAdv() {
		LOG.info("ViewAdvertises");
		
		List<Advertise> list =  service.getAllAdvertises();
		return new ResponseEntity<List>(list,HttpStatus.FOUND);
		
	}

//	seller can view the specific advertise  by id posted by seller
	@GetMapping("/user/seller/getAdv/{advid}")
	public ResponseEntity<Advertise> getAdvertiseByadvid(@PathVariable("advid") int advid) {
		LOG.info("advertise");
		Advertise adv = service.findAdvertiseByadvid(advid);
		HttpHeaders headers = new HttpHeaders();
		if (adv != null) {
			headers.add("Advertise name", adv.getAdvertisetitle());
			return new ResponseEntity<Advertise>(adv, headers, HttpStatus.OK);
		} else {
			headers.add("Advertise name", "Category not available");
			return new ResponseEntity<Advertise>(adv, headers, HttpStatus.NOT_FOUND);
		}
	}

	// Seller can delete his posted advertise
	@DeleteMapping("/user/seller/deleteAdv/{advid}")
	public ResponseEntity<?> deleteAdv(@PathVariable("advid") int advid) {
		LOG.info("deleteadvertise");
			service.deleteAdvertise(advid);
			return new ResponseEntity<String>("Advertisement deleted successfully",HttpStatus.OK);
	}
}