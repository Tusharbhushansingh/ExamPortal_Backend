package com.exam.repository.quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.model.User;
import com.exam.model.quiz.Quiz;
import com.exam.model.quiz.QuizAttempt;

@Repository
public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long> {
	public List<QuizAttempt> findByUser(User user);
	
	public List<QuizAttempt> findByQuiz(Optional<Quiz> quiz);
	
	public List<QuizAttempt> findByUserAndQuiz(User user, Quiz quiz);

	public QuizAttempt findByUserAndQuiz(Optional<User> user, Optional<Quiz> quiz);
	
}
