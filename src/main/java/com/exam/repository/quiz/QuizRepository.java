package com.exam.repository.quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.exam.model.quiz.Category;
import com.exam.model.quiz.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	@Modifying
	@Transactional
	@Query(value="DELETE FROM quiz WHERE qid =?1", nativeQuery = true)
	public void deleteQuiz(Long qid);
	

	public List<Quiz> findByCategory(Category category);
	
	public List<Quiz> findByActive(Boolean b);
	
	public List<Quiz> findByCategoryAndActive(Category category, Boolean b);


	public Optional<Quiz> findById(Long quizId);
	
	
}
