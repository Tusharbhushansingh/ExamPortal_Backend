package com.exam.controller.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.User;
import com.exam.model.quiz.Quiz;
import com.exam.model.quiz.QuizAttempt;
import com.exam.service.quiz.QuizAttemptService;

@RestController
@CrossOrigin("*")
@RequestMapping("/attempt")
public class QuizAttemptController {
	
	@Autowired
	private QuizAttemptService quizAttemptService;

//	@PutMapping("/")
//	public ResponseEntity<QuizAttempt> addQuizAttempt(@RequestBody User user,@RequestBody Quiz quiz){
//		return ResponseEntity.ok(this.quizAttemptService.addAttempt(user, quiz));
//	}
//	
	
	@PostMapping("/{userId}/{quizId}")
	public void addQuizAttempt(@PathVariable Long userId, @PathVariable Long quizId){
		this.quizAttemptService.add(userId, quizId);
	}
	
	@GetMapping("/{userId}/{quizId}")
	public Long getQuizAttempt(@PathVariable Long userId, @PathVariable Long quizId){
		return this.quizAttemptService.getAttempts(userId, quizId);
	}
	
	
	@GetMapping("/admin/{quizId}")
	public ResponseEntity<List<QuizAttempt>> getQuizAttemptByAdmin(@PathVariable Long quizId){
		return ResponseEntity.ok(this.quizAttemptService.getAllAttempts(quizId));
	}
	
	@GetMapping("/user/")
	public ResponseEntity<List<QuizAttempt>> getUserAttempts(@RequestBody User user){
		return ResponseEntity.ok(this.quizAttemptService.getUserAttempts(user));
	}
	
	@GetMapping("/user/quiz/")
	public ResponseEntity<QuizAttempt> getUserSpecificQuizAttempt(@RequestBody User user, @RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizAttemptService.getUserSpecificQuizAttempt(user,quiz));
	}
}
