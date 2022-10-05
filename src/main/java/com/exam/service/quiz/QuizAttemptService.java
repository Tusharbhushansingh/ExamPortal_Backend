package com.exam.service.quiz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.quiz.Quiz;
import com.exam.model.quiz.QuizAttempt;

@Service
public interface QuizAttemptService {

	public QuizAttempt addAttempt(User user, Quiz quizId);
	
	
	public List<QuizAttempt> getUserAttempts(User user);
	
	public QuizAttempt getUserSpecificQuizAttempt(User user, Quiz quiz);
	
	public QuizAttempt add(Long userId, Long quizId);
	
	public Long getAttempts(Long userId, Long quizId);

	public List<QuizAttempt> getAllAttempts(Long quizId);
	
}
