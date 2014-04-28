package org.tassoni.quizspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "answers")
public class Answer extends BaseEntity{
	@ManyToOne(optional = false)
	@JoinColumn(name = "questionId")
	private Question question;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "choiceId")
	private Choice choice;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "userid")
	private User user;
	
	@Column(name = "creation_time", nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime creationTime;
	  
	public Question getQuestion() {
		return question;
	}

	public Answer setQuestion(Question question) {
		this.question = question;
		return this;
	}

	public Choice getChoice() {
		return choice;
	}

	public Answer setChoice(Choice choice) {
		this.choice = choice;
		return this;
	}
	
	public User getUser() {
		return user;
	}

	public Answer setUser(User user) {
		this.user = user;
		return this;
	}

	public DateTime getCreationTime() {
		return creationTime;
	}
	
	//We don't expect Answers to be updated, only to be created, so we don't have a
	//modification time, only a creation time
	@PrePersist
	public void prePersist() {
		creationTime = DateTime.now();
	}
	
}