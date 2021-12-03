package newspaper.advertisement.system.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "advertise_adv")
public class Advertise {

	@JsonIgnore
	@Id
	@Column(name = "advid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "advid_generator")
	@SequenceGenerator(name = "advid_generator", initialValue = 201, allocationSize = 1, sequenceName = "advId_seq")
	private int advid;
	
	@Column(name = "advertisetitle", length = 50)
	private String advertisetitle;

	@JsonIgnore
	@Column(name = "price", length = 10)
	private double price;

	@Column(name = "description", length = 40)
	private String description;

	@JsonIgnore
	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "advownername", length = 20)
	private String advownername;
	
	@Column(name ="newspaper_name", length = 20)
	private String newsPaperName;
	
	@JsonIgnore
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name="imageUrl")
	private String imageUrl;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "catid")
	private Category category;

	public Advertise() {
		super();
	}

	

	 

	

	public Advertise(String advertisetitle, double price, String description, String advownername, String imageUrl
			) {
		super();
		this.advertisetitle = advertisetitle;
		this.price = price;
		this.description = description;
		this.advownername = advownername;
		this.imageUrl = imageUrl;
		
	}



	public Advertise(int advid, String advertisetitle, double price, String description, String advownername,
			String imageUrl, Category category) {
		super();
		this.advid = advid;
		this.advertisetitle = advertisetitle;
		this.price = price;
		this.description = description;
		this.advownername = advownername;
		this.imageUrl = imageUrl;
		this.category = category;
	}

	public Advertise(int advid,  String advertisetitle, double price, String description, String status,
			String advownername, String imageUrl, Category category) {
		super();
		this.advid = advid;
		this.advertisetitle = advertisetitle;
		this.price = price;
		this.description = description;
		this.status = status;
		this.advownername = advownername;
		this.imageUrl = imageUrl;
		this.category = category;
	}


	public String getNewsPaperName() {
		return newsPaperName;
	}


	public void setNewsPaperName(String newsPaperName) {
		this.newsPaperName = newsPaperName;
	}







	public LocalDate getDate() {
		return date;
	}







	public void setDate(LocalDate date) {
		this.date = date;
	}







	public Advertise(int advid, String status) {
		super();
		this.advid = advid;
		this.status = status;
	}
	
	public int getAdvid() {
		return advid;
	}

	public void setAdvid(int advid) {
		this.advid = advid;
	}


	public String getAdvertisetitle() {
		return advertisetitle;
	}


	public void setAdvertisetitle(String advertisetitle) {
		this.advertisetitle = advertisetitle;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdvownername() {
		return advownername;
	}

	public void setAdvownername(String advownername) {
		this.advownername = advownername;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category2) {
		this.category = category2;
	}


	@Override
	public String toString() {
		return "Advertise [advid=" + advid  + ", advertisetitle=" + advertisetitle + ", price="
				+ price + ", description=" + description + ", status=" + status + ", advownername=" + advownername
				+ ", imageUrl=" + imageUrl + ", category=" + category + "]";
	}
	

	

	

	

}