package com.exam.service.quiz;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.quiz.Category;
import com.exam.model.quiz.Quiz;

@Service
public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz updateQuiz(Quiz quiz);
	
	public Set<Quiz> getQuizzes();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);

	public List<Quiz> getQuizzesOfCategory(Category category);

	public List<Quiz> getActiveQuizzes();
	
	public List<Quiz> getActiveQuizzessOfCategory(Category category);
	
	
}
