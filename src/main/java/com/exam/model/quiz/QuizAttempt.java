package com.exam.model.quiz;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.exam.model.User;

@Entity
public class QuizAttempt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long quizAttemptid;
	
	private Long numberOfQuizAttempts;
	
	//user
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	
	//quiz
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;
	

	public QuizAttempt() {
		super();
		// TODO Auto-generated constructor stub
	}



	public QuizAttempt(Long numberOfQuizAttempts, User user, Quiz quiz) {
		super();
		this.numberOfQuizAttempts = numberOfQuizAttempts;
		this.user = user;
		this.quiz = quiz;
	}



	public Long getQuizAttemptid() {
		return quizAttemptid;
	}


	public void setQuizAttemptid(Long quizAttemptid) {
		this.quizAttemptid = quizAttemptid;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Quiz getQuiz() {
		return quiz;
	}


	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


	public Long getNumberOfQuizAttempts() {
		return numberOfQuizAttempts;
	}


	public void setNumberOfQuizAttempts(Long numberOfQuizAttempts) {
		this.numberOfQuizAttempts = numberOfQuizAttempts;
	}
}
