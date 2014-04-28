package org.tassoni.quizspring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8687165321701048865L;

	@Column(unique=true, nullable = false)
	private String username;
	
	@Column(nullable = false)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}