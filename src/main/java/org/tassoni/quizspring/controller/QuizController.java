package org.tassoni.quizspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tassoni.quizspring.model.Answer;
import org.tassoni.quizspring.model.Choice;
import org.tassoni.quizspring.model.Question;
import org.tassoni.quizspring.model.QuizContent;
import org.tassoni.quizspring.model.User;
import org.tassoni.quizspring.security.SecurityUtil;
import org.tassoni.quizspring.security.UserNotAuthenticatedException;
import org.tassoni.quizspring.service.QuizService;


@Controller
public class QuizController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);
	
	public static final String USER_KEY = "user";
	private static final String LOGIN_URL = "/login/{username}";
	
	private QuizService quizService;
	
	@Autowired
	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}
    
    @ExceptionHandler(UserNotAuthenticatedException.class)
    ResponseEntity<String> handleMissingUser(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    //If the user has logged in and is resuming the quiz after being away long enough that the gui doen't know
    //the user's next question, make this RESTful call.
    @RequestMapping(value = "/api/quiz/next", method = RequestMethod.GET)
    @ResponseBody
    public Question nextQuestionBasedOnlyOnUser() {
    	Question question =  quizService.findNextQuestion(SecurityUtil.getUser());
    	return question;
    }
    

    @RequestMapping(value = "/api/quiz/question/{questionId}/choice/{choiceId}", method = RequestMethod.PUT)
	public ResponseEntity<QuizContent> answerQuestion(
			@PathVariable Long questionId, @PathVariable Long choiceId) {
		User user =SecurityUtil.getUser();
		Answer answer = quizService.saveAnswer(new Answer()
				.setUser(user)
				.setChoice(
						quizService.stubReferenceForId(Choice.class, choiceId))
				.setQuestion(
						quizService.stubReferenceForId(Question.class,
								questionId)));
		LOGGER.debug("Saved answer has id " + answer.getId());
		//TODO Does the client care about the id of the answer just created?
		// TODO If no quizContent found, we have internal server error.
		// And looks like we need to URL encode the data! I see apostrophes
		// getting mangled.
		return new ResponseEntity<QuizContent>(
				quizService.findQuizContentByChoiceId(choiceId), HttpStatus.OK);

	}

    
    

}
