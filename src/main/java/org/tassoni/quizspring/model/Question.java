package org.tassoni.quizspring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "questions")
//Note: Json serialization would cause a stack overflow if we didn't have this annotation on both Question and Choice, since they have a bi-directional relationship
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Question extends BaseEntity {
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "quizid")
	private Quiz quiz;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
	private List<Choice> choices;

	@Column
	private String text;

	@Column(name = "why_important")
	private String whyImpportant;
	
	@Column
	private String edge;
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void addChoice(Choice choice) {
		if(choices == null)
			choices = new ArrayList<Choice>();
		choices.add(choice);
		choice.setQuestion(this);
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getWhyImpportant() {
		return whyImpportant;
	}

	public void setWhyImpportant(String whyImpportant) {
		this.whyImpportant = whyImpportant;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}		
	
}
