package newspaper.advertisement.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_adv")
public class AppUser {

	@JsonIgnore
	@Id
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_generator")
	@SequenceGenerator(name = "userid_generator", initialValue = 101, allocationSize = 1, sequenceName = "userId_seq")
	private int userid;

	@Column(name = "password", length = 30)
	private String password;

	@Column(name = "firstName", length = 20)
	private String firstName;

	@Column(name = "lastName", length = 20)
	private String lastName;

	@Column(name = "address", length = 40)
	private String address;

	@Column(name = "contactno", length = 15)
	private String contactno;

	@Column(name = "email", nullable = false, length = 30)
	private String email;

	@JsonIgnore
	private Role role;


	public AppUser() {
		super();
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	
}