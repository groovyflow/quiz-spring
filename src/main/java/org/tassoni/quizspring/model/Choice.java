package org.tassoni.quizspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



@Entity
@Table(name = "choices")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Choice extends BaseEntity{
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "questionid")
	private Question question;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_contentid")
	private QuizContent quizContent;
	
	@Column
	private String text;
	

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public QuizContent getQuizContent() {
		return quizContent;
	}

	public void setQuizContent(QuizContent quizContent) {
		this.quizContent = quizContent;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	


}
