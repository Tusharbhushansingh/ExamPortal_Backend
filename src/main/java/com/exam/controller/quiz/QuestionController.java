package com.exam.controller.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.exam.model.quiz.Question;
import com.exam.model.quiz.Quiz;
import com.exam.service.quiz.QuestionService;
import com.exam.service.quiz.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	//add question
	@PostMapping("/")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	//update question
	@PutMapping("/")
	public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	//get questions of quiz
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable Long qid){
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		
		List<Question> list = new ArrayList<>(questions);
		if(list.size() > Integer.parseInt(quiz.getNumberOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestions()));
		}
		list.forEach((q)->{
			q.setAnswer("");
		});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);
	}
	
	//get question
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable Long quesId) {
		return this.questionService.getQuestion(quesId);
	}
	
	//delete question
	@DeleteMapping("/{quesId}")
	public void deleteQuestion(@PathVariable Long quesId) {
		this.questionService.deleteQuestion(quesId);
	}
	
	//get questions of quiz
		@GetMapping("/quiz/all/{qid}")
		public ResponseEntity<?> getQuestionsOfAdmin(@PathVariable Long qid){
			Quiz quiz = this.quizService.getQuiz(qid);
			Set<Question> questions = quiz.getQuestions();
			List<?> list = new ArrayList<>(questions);
			return ResponseEntity.ok(list);
		}
		
	//eval quiz
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
		  Double marksGot=0.0;
		  Integer correctAnswer=0;
		  Integer attempted=0;
		System.out.println(questions);
		for(Question q: questions){
			//single questions
			Question question = this.questionService.get(q.getQuesid());
			if(question.getAnswer().equals(q.getGivenAnswer())) {
				//correct
				correctAnswer++;
				
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot += marksSingle;
				
			}
			if(q.getGivenAnswer()!=null) {
				attempted++;
			}
		}
		Map<String, Object> map = Map.of("marksGot",marksGot,"correctAnswer",correctAnswer,"attempted",attempted);
		return ResponseEntity.ok(map);
	}
		
}
