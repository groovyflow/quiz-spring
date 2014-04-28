package org.tassoni.quizspring.repository;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tassoni.quizspring.model.Answer;
import org.tassoni.quizspring.model.BaseEntity;
import org.tassoni.quizspring.model.Choice;
import org.tassoni.quizspring.model.Question;
import org.tassoni.quizspring.model.QuizContent;
import org.tassoni.quizspring.model.User;


class BasicRepositoryImpl implements BasicRepository{
	//Note: You can see whether the JPA second level cache has your entity like so:
	//eM.getEntityManagerFactory().getCache().contains(Class cls, Object  primaryKey)
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicRepositoryImpl.class);
	
	@PersistenceContext
	protected EntityManager eM;
	
	public <T extends BaseEntity> T save(T entity) {
		
		if(entity.isNew())
			eM.persist(entity);
		else {
			eM.merge(entity);
		}
		return entity;
	}
	
	//Note: Neat trick! See http://stackoverflow.com/questions/16043761/java-hibernate-entity-allow-to-set-related-object-both-by-id-and-object-itself
	//For Hibernate the trick is session.load(SomeEntity.class, id);
	public <T extends BaseEntity> T stubReferenceForId(Class<T> clazz, Long id) {
		return eM.getReference(clazz, id);
	}
	
	public <T extends BaseEntity> T findById(Class<T> clazz, Long id) {
		return eM.find(clazz, id);
	}
	
	/**
	 * 
	 * Please don't call this in a loop!
	 * If you already have the entity that you want to remove, and if you are in a transaction,
	 * then you wouldn't use this. (Instead use remove(BaseEntity.)  You use this when you're outside a transaction, because
	 * JPA cannot delete a detached entity.
	 */
	public <T extends BaseEntity> void remove(Class<T> clazz, Long id) {
	  remove(findById(clazz, id));
	}
	
	public void remove(BaseEntity entity) {
		eM.remove(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> List<T> findAll(Class<T> clazz) {
		Query query = eM.createQuery("select x from " + clazz.getSimpleName() + " x " );
		return query.getResultList();
	}
	
	
	public QuizContent findQuizContentByChoiceId(Long choiceId) {
		 String queryString = "select quizContent from QuizContent quizContent, Choice choice where choice.id = :id and choice.quizContent = quizContent";
		 TypedQuery<QuizContent> query = eM.createQuery(queryString, QuizContent.class);
		 query.setParameter("id", choiceId);
		 return query.getSingleResult();
	}
	
	public Answer latestAnswerForUser(User user) {
		if(user == null)
			throw new IllegalArgumentException("Null user");
		TypedQuery<Answer> query = eM.createQuery("select answer from Answer answer, User user where answer.user = :user" + 
	         " order by answer.creationTime desc" , Answer.class);	
		query.setMaxResults(1);
		query.setParameter("user", user);
		query.setMaxResults(1);
		LOGGER.debug("Finding next answer for user with id =  " + user.getId());
		List<Answer> answers = query.getResultList();
		return answers.isEmpty() ? null : answers.iterator().next();
		//Could have done getSingleResult, but didn't because we don't want "no entity found for query" exception here: return query.getSingleResult();
	}
	public Question findNextQuestionAndChoices( Answer answer){	
		TypedQuery<Question> query = eM.createQuery("select distinct question2 from  Question question2 inner join fetch question2.choices choices,  NextQuestion nextQuestion, Question question where"
    		+ "  nextQuestion.question = :question and nextQuestion.choice = :choice and"
			+ " nextQuestion.nextQuestion = question2 order by choices.id", Question.class);
		LOGGER.debug("Finding next question for questionId " + answer.getQuestion().getId() + " and choice " + answer.getChoice().getId());
		query.setParameter("question", answer.getQuestion());
		query.setParameter("choice", answer.getChoice());
		return query.getSingleResult();
	}
	
	public Question findQuestionAndChoices(Long questionId) {
		TypedQuery<Question> query = eM.createQuery("select question from Question question inner join fetch question.choices choices where question.id = :id order by choices.id", 
				Question.class);
		query.setParameter("id", questionId);
	    return query.getSingleResult();
	}
	
	public User findUserByUsernameAndPassword(String username, String password) {
		TypedQuery<User> query = eM.createQuery("select user from User user where username = :username and password = :password", User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getSingleResult();
	}
	
	public User findUserByUsername(String username) {
		TypedQuery<User> query = eM.createQuery("select user from User user where username = :username", User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}	
	
	
	public <T extends BaseEntity> T genericSingle(final String queryString, final Map<String, Object> params, final Class<T> clazz) {
		TypedQuery<T> query = eM.createQuery(queryString, clazz);
		for(Map.Entry<String, Object> entry  : params.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return  query.getSingleResult();		
	}
	
	

}
