package org.tassoni.quizspring.repository;


//We test queries out here, and we don't need a service to run the queries in a transaction.
//Instead we simply run queries inside our test transaction manager
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tassoni.quizspring.model.*;
import org.tassoni.quizspring.repository.BasicRepository;


@ContextConfiguration(locations = { "/exampleApplicationContext-persistence.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkOnQueriesTest {
	
	@Autowired
	private BasicRepository basicRepository;
	
	@Test
	public void nextQuestion() {
		String queryString = "select distinct question2 from  Question question2 inner join fetch question2.choices choices,  NextQuestion nextQuestion, Question question where question.id = :id and nextQuestion.question = question and"
				+ " nextQuestion.nextQuestion = question2 order by choices.id";

		Question nextQuestion = basicRepository.genericSingle(queryString,
				new HashMap<String, Object>() {
					{
						put("id", 1L);
					}
				}, Question.class);
		
		assertTrue(nextQuestion.getText().contains("Do you have a resume?"));
		List<Choice> choices = nextQuestion.getChoices();
		for(Choice choice : nextQuestion.getChoices()) {
			System.out.println("choice text is " + choice.getText() + " and choice id is " + choice.getId());
		}
		assertEquals(2, choices.size());
		assertEquals("No", choices.iterator().next().getText());
		assertEquals("Yes", choices.get(1).getText());

		System.out.println(choices);

	}
	
	
	@Test
	public void testFindQuizContentFromChoice() {
	  String queryString = "select quizContent from QuizContent quizContent, Choice choice where choice.id = :id and choice.quizContent = quizContent";
		//String queryString = "select choice from  Choice choice join fetch quizContent QuizContent where choice.id = :id";	
	  QuizContent quizContent = basicRepository.genericSingle(queryString, new HashMap<String, Object>() {
		  {
			  put("id", 2L);
		  }
	  }, 
	  QuizContent.class);
	  assertTrue(quizContent.getMain().startsWith("You know a potential employer will review"));
	  
	}
	
	
	
}
