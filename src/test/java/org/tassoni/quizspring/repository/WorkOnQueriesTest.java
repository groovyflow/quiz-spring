package org.tassoni.quizspring.repository;


//We test queries out here, and we don't need a service to run the queries in a transaction.
//Instead we simply run queries inside our test transaction manager
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.tassoni.quizspring.model.*;
import org.tassoni.quizspring.repository.BasicRepository;

import com.google.common.base.FinalizablePhantomReference;

@ContextConfiguration(locations = { "/exampleApplicationContext-persistence.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class WorkOnQueriesTest {
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private BasicRepository basicRepository;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	private TransactionTemplate transactionTemplate;
	private EntityManager entityManager;
	
	
	
	@Before
	public void init() {
		this.transactionTemplate = new TransactionTemplate(transactionManager);
		entityManager = entityManagerFactory.createEntityManager();
	}


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
