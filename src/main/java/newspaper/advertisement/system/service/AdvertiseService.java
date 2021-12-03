package newspaper.advertisement.system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import newspaper.advertisement.system.exception.AdvertiseNotFoundException;
import newspaper.advertisement.system.exception.CategoryNotFoundException;
import newspaper.advertisement.system.exception.DataNotFoundException;
import newspaper.advertisement.system.model.Advertise;
import newspaper.advertisement.system.model.Category;
import newspaper.advertisement.system.repository.AdvertiseRepository;
import newspaper.advertisement.system.repository.CategoryRepository;

/**
 * 
 * service for Advertise functionalities
 *
 */

@Service
public class AdvertiseService {

	private static final Logger LOG = LoggerFactory.getLogger(AdvertiseService.class);

	@Autowired
	private AdvertiseRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;

//	Post New Advertise(Selling)
	public Advertise addAdvertise(Advertise adv) {
		LOG.info("addProduct");
		adv.setDate(LocalDate.now());
		return repository.save(adv);
		
	}
	
	public void updateAdvertise(  Advertise adv  ) {
		LOG.info("updateProduct");
	 if(repository.existsById(adv.getAdvid())) {
		 repository.save(adv);
	 }
	 else
		 throw new AdvertiseNotFoundException("Advertisement not found");
	}	
	
//	Search Advertise by text entered in textbox(seller)
	public List<Advertise> getAdvertiseByName(String advertisetitle) {
		LOG.info("findAdvertiseByName");
		return repository.findByadvertisetitle(advertisetitle);
	}

//	 view advertise all Advertise
	public List<Advertise> getAllAdvertises() {
		LOG.info("ViewAllAdvertises");
		List<Advertise> list =repository.findAll();
		if(list.isEmpty())
			throw new DataNotFoundException("Data not found");
		return list;
	}

//	Read the specific advertise by id(seller)
	public Advertise findAdvertiseByadvid(int advid) {
		LOG.info("findAdvertiseById");
		Optional<Advertise> optAdv = repository.findByadvid(advid);
		if (optAdv.isPresent())
			return optAdv.get();
		else
			throw new AdvertiseNotFoundException("Advertisement not found");
			
	}


	

//	Admin will update status of advertise
	public void updateAdvStatus(int advid, String status) {
		LOG.info("UpdateStatus");
		if(repository.existsById(advid))
		repository.updateStatusAdv(status, advid);
		else
			throw new AdvertiseNotFoundException("Advertisement not found with id"+ advid);
	}

//	Delete product by id
	public void deleteAdvertise(int advid) {
		LOG.info("deleteProduct-service");
		repository.deleteById(advid);

	}

//	show approved advertise for buyer
	public List<Advertise> getApprovedAdvs() {
		LOG.info("ApprovedAdvertise");
		List<Advertise> list = repository.viewApprovedAdv();
		/*if(list.isEmpty()) {
			throw new DataNotFoundException("Data not found");
		}
		else {
		List<Advertise> list2 = list.stream().filter(e -> e.getStatus() == "APPROVED")
				.collect(Collectors.toList());
		return list2;
		//}*/
				return list;
	}

//	 user can also search for advertises by advertisetitle	
	public List<Advertise> findAdvertiseByadvertisetitle(String advertisetitle) {
		LOG.info("findAdvertiseByadvertisetitle");
		return repository.findByadvertisetitle(advertisetitle);
	}

	

}
