package org.tassoni.quizspring.repository;

import java.util.List;







import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tassoni.quizspring.model.Answer;
import org.tassoni.quizspring.model.BaseEntity;
import org.tassoni.quizspring.model.Question;
import org.tassoni.quizspring.model.QuizContent;
import org.tassoni.quizspring.model.User;

public interface BasicRepository {
	
	<T extends BaseEntity> T save(T entity);
	<T extends BaseEntity> T stubReferenceForId(Class<T> clazz, Long id);
	<T extends BaseEntity> T findById(Class<T> clazz, Long id);
	<T extends BaseEntity> List<T> findAll(Class<T> clazz);
	
	<T extends BaseEntity> void remove(Class<T> clazz, Long id);
	void remove(BaseEntity entity);
	
	public QuizContent findQuizContentByChoiceId(Long choiceId);
	Answer latestAnswerForUser(User user);
	Question findNextQuestionAndChoices( Answer answer);
	Question findQuestionAndChoices(Long questionId);
	User findUserByUsernameAndPassword(String username, String password);
	User findUserByUsername(String username);
	<T extends BaseEntity> T genericSingle(final String queryString, final Map<String, Object> params, final Class<T> clazz);



}
