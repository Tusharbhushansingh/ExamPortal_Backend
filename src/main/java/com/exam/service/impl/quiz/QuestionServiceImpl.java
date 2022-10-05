package com.exam.service.impl.quiz;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.quiz.Question;
import com.exam.model.quiz.Quiz;
import com.exam.repository.quiz.QuestionRepository;
import com.exam.service.quiz.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;
	
	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestions() {
		return new LinkedHashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long quizId) {
		return this.questionRepository.findById(quizId).get();
	}

	@Override
	public Set<Question> getQuestionofQuiz(Quiz quiz) {
		return this.questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quizId) {
		if (this.questionRepository.findById(quizId) != null) {
			this.questionRepository.deleteById(quizId);
		}
		
	}

	@Override
	public Question get(Long quesId) {
		return this.questionRepository.getOne(quesId);
	}

}
