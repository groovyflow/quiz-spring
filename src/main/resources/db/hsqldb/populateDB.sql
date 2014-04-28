INSERT INTO QUIZES(id, name, version) values(1, 'IntroQuiz', '1.0');

INSERT INTO QUESTIONS(id, quizid, text, why_important, edge) values(1, 1, 'Do you have a LinkedIn account?', null, null);


INSERT INTO QUIZCONTENTS(id, main) VALUES (1, 'Did you know a potential employer would look at your social profile? Here are how to’s for presenting yourself appropriately online and how to avoid being embarrassed if an employer Googled you. (video, and tip sheet on how to present self right way) ');
INSERT INTO QUIZCONTENTS(id, main) VALUES (2, 'You know a potential employer will review your social profiles and your LinkedIn page. Here are how to’s for presenting yourself appropriately online and ensuring your social media profiles won’t keep you from getting the job. Plus, how to’s for a great likedin profile that will help you stand out. (video, and tip sheet on how to present self right way)  ');

INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(1, 1, 1, 'No');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(2, 1, 2, 'Yes');



INSERT INTO QUESTIONS(id, quizid, text, why_important, edge) values(2, 1, 'Do you have a resume? ', null, 'Want an edge? Create a business card or leave behind' );
--The bridge between first question and second question
--For this question both choices point to the same next question
INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(1, 1, 2, 1);
INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(2, 1, 2, 2);

INSERT INTO QUIZCONTENTS(id, main) VALUES (3, 'does it include all work experience, social activities, interests? Click here for an example of a good resume ');
INSERT INTO QUIZCONTENTS(id, main) VALUES (4, 'click here for videos, examples, etc. on what your resume should include, ');

INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(3, 2, 3, 'No');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(4, 2, 4, 'Yes');



INSERT INTO QUESTIONS(id, quizid, text, why_important, edge) values(3, 1, 'What are you most afraid of about networking? ', null, 'Want a competitive edge? Create a marketing plan ' );
INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(3, 2, 3, 3);
INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(4, 2, 3, 4);

INSERT INTO QUIZCONTENTS(id, main) VALUES (5, 'content for how to network, click on this when your main fear about networking is wasting the time of that person');
INSERT INTO QUIZCONTENTS(id, main) VALUES (6, 'video on networking questions, click on this when your fear about networking is asking the right question');
INSERT INTO QUIZCONTENTS(id, main) VALUES (7, 'video and content about finding the right people to network with');
INSERT INTO QUIZCONTENTS(id, main) VALUES (8, 'Whatever content we have for Networking: What are you most afraid of? Finding WHere I want to work');
INSERT INTO QUIZCONTENTS(id, main) VALUES (9, 'Whatever content we have for Networking: What are you most afraid of? I have no idea.');

INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(5, 3, 5, 'Taking someone’s time ');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(6, 3, 6, 'Asking the right questions  ');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(7, 3, 7, 'Finding the right people  ');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(8, 3, 8, 'Finding where I want to work');
INSERT INTO CHOICES(id, questionid, quiz_contentid, text) values(9, 3, 9, 'I have no idea');


-- INSERT INTO QUESTIONS(id, quizid, text, why_important, edge) values(4, 1, 'Have you had an interview? ', null, 'Want an edge? close and get another interview/someone else to talk to, follow up creatively for example… tailor thank you note/personalize, bring something you’re proud of to the interview like a presentation you did for school, etc.  ' );
--INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(5, 3, 4, 5);
--INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(5, 3, 4, 6);
--INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(5, 3, 4, 7);
--INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(5, 3, 4, 8);
--INSERT INTO NEXTQUESTIONS(id, questionid, next_questionid, choiceid) values(5, 3, 4, 9);

--INSERT INTO QUIZCONTENTS(id, main) VALUES (10, 'content should cover – what to wear, how to follow up, content should link to questions to ask/closing, tips for what to wear /cross link to business etiquette');
--INSERT INTO QUIZCONTENTS(id, main) VALUES (11, 'video on networking questions, click on this when your fear about networking is asking the right question');




--For now let's create a user here. TODO Once we have security code we don't need to start out with a user.
INSERT INTO USERS(id, username, password) values(1, 'Chuck', 'thePassword')