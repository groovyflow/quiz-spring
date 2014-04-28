package org.tassoni.quizspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "quizcontents")
public class QuizContent extends BaseEntity{
	

	@Column
	@Length(max = 5000)
	@NotEmpty
	private String main;
	
	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

}
