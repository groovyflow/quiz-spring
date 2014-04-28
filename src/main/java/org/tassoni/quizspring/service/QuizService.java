package org.tassoni.quizspring.service;

import java.util.List;

import org.tassoni.quizspring.model.Answer;
import org.tassoni.quizspring.model.BaseEntity;
import org.tassoni.quizspring.model.Question;
import org.tassoni.quizspring.model.QuizContent;
import org.tassoni.quizspring.model.User;

public interface QuizService {
	<T extends BaseEntity> T stubReferenceForId(Class<T> clazz, Long id);
	QuizContent findQuizContentByChoiceId(Long id) ;
	User findUserByUsernameAndPassword(String username, String password);
	<T extends BaseEntity> List<T> findAll(Class<T> clazz);
	<T extends BaseEntity> T findOne(Class<T> clazz, Long id);
	Answer saveAnswer(Answer answer) ;
	Question findNextQuestion(User user);
}
