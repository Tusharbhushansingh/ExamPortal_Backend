package com.exam.model.quiz;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long qid;
	
	private String title;
	
	private String description;
	
	private String maxMarks;
	
	private String numberOfQuestions;
	
	private Boolean active = false;

	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	
	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Question> questions = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "quiz")
	@JsonIgnore
	private Set<QuizAttempt> userQuizAttempts = new HashSet<>();
	
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(String numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public long getQid() {
		return qid;
	}

	public void setQid(long qid) {
		this.qid = qid;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Set<QuizAttempt> getUserQuizAttempts() {
		return userQuizAttempts;
	}

	public void setUserQuizAttempts(Set<QuizAttempt> userQuizAttempts) {
		this.userQuizAttempts = userQuizAttempts;
	}

	public Quiz(String title, String description, String maxMarks, String numberOfQuestions, Boolean active,
			Category category, Set<Question> questions, Set<QuizAttempt> userQuizAttempts) {
		super();
		this.title = title;
		this.description = description;
		this.maxMarks = maxMarks;
		this.numberOfQuestions = numberOfQuestions;
		this.active = active;
		this.category = category;
		this.questions = questions;
		this.userQuizAttempts =  userQuizAttempts;
	}
}
