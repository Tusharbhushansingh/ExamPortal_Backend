package com.exam.service.quiz;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exam.model.quiz.Question;
import com.exam.model.quiz.Quiz;

@Service
public interface QuestionService {

	public Question addQuestion(Question question);
	
	public Question updateQuestion(Question question);
	
	public Set<Question> getQuestions();
	
	public Question getQuestion(Long quizId);
	
	public Set<Question> getQuestionofQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quizId);
	
	public Question get(Long quesId);
	
	public void saveFromFile(MultipartFile multipartFile, Long quizId);
}
