package org.tassoni.quizspring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nextquestions")
public class NextQuestion extends BaseEntity{
	@ManyToOne(optional = false)
	@JoinColumn(name = "questionid")
	private Question question;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "next_questionid")	
	private Question nextQuestion;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "choiceid")
	private Choice choice;

}
