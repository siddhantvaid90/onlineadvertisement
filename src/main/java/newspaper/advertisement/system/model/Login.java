package newspaper.advertisement.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "login_adv")
public class Login {

	@Id
	@Column(name = "email", nullable = false, unique = true, length = 30)
	private String email;
	private String password;
	@JsonIgnore
	private Role role;
	
	public Login() {
		super();
	}
	
	
	public Login(String email, String password, Role role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
