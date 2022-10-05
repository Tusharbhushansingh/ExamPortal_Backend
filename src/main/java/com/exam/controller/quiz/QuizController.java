package com.exam.controller.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.quiz.Category;
import com.exam.model.quiz.Quiz;
import com.exam.service.quiz.QuizService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	
	//add quiz
	@PostMapping("/")
	public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	//update quiz
	@PutMapping("/")
	public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){

		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	//get quizzes
	@GetMapping("/")
	public ResponseEntity<?> getQuizzes(){
		return ResponseEntity.ok(this.quizService.getQuizzes());
	}
	
	//get quiz
	@GetMapping("/{qid}")
	public Quiz getQuiz(@PathVariable Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	//delete quiz
	@DeleteMapping("/{qid}")
	public void deleteQuiz(@PathVariable Long qid) {
		this.quizService.deleteQuiz(qid);
	}
	
	//get quiz on the basis of category
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesofCategory(@PathVariable Long cid){
		
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getQuizzesOfCategory(category);
	}
	
	//get active quizzes
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzess(){
		return this.quizService.getActiveQuizzes();
	}
	
	//get active quizzes of category
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzes(@PathVariable Long cid){
		Category category = new Category();
		category.setCid(cid);
		return this.quizService.getActiveQuizzessOfCategory(category);
	}
	
}
