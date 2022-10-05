package com.exam.service.impl.quiz;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.quiz.Quiz;
import com.exam.model.quiz.QuizAttempt;
import com.exam.repository.UserRepository;
import com.exam.repository.quiz.QuizAttemptRepository;
import com.exam.repository.quiz.QuizRepository;
import com.exam.service.quiz.QuizAttemptService;

@Service
public class QuizAttemptServiceImpl implements QuizAttemptService {

	@Autowired
	private QuizAttemptRepository quizAttemptRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private QuizRepository quizRepository;
	
	@Override
	public QuizAttempt addAttempt(User user, Quiz quiz) {
		Long attempts = 1l;
		QuizAttempt quizAttempt = new QuizAttempt();
		quizAttempt.setQuiz(quiz);
		quizAttempt.setUser(user);
		
		QuizAttempt attemptsQuiz =(QuizAttempt) this.quizAttemptRepository.findByUserAndQuiz(user, quiz);
		
		//record already exist so just need to update the attempt by 1.
		if(attemptsQuiz != null) {
			quizAttempt.setQuizAttemptid(attemptsQuiz.getNumberOfQuizAttempts());
			attempts = attemptsQuiz.getNumberOfQuizAttempts();
			attempts++;
		}
		
		quizAttempt.setNumberOfQuizAttempts(attempts);
		return this.quizAttemptRepository.save(quizAttempt);
	}

	@Override
	public List<QuizAttempt> getUserAttempts(User user) {
		return this.quizAttemptRepository.findByUser(user);
	}

	@Override
	public QuizAttempt getUserSpecificQuizAttempt(User user, Quiz quiz) {
		return (QuizAttempt) this.quizAttemptRepository.findByUserAndQuiz(user, quiz);
	}

	@Override
	public QuizAttempt add(Long userId, Long quizId) {
		Long attempts = 1l;
		QuizAttempt quizAttempt = new QuizAttempt();
		
		System.out.println(userId +"-----"+quizId);
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user == null) {
		System.out.println("null aa rha hai bhai");
		}
		else {
			System.out.println("null nhi aa rha hai!!");
		}
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		
		System.out.println("user aur quiz null nhi hai!!");
		QuizAttempt attemptsQuiz =(QuizAttempt) this.quizAttemptRepository.findByUserAndQuiz(user, quiz);
		
		if(user != null & quiz != null) {
			
			//System.out.println("user aur quiz null nhi hai!!");
			//QuizAttempt attemptsQuiz =(QuizAttempt) this.quizAttemptRepository.findByUserAndQuiz(user, quiz);
			
			
			
			//record already exist so just need to update the attempt by 1.
			if(attemptsQuiz != null) {
				quizAttempt.setQuizAttemptid(attemptsQuiz.getNumberOfQuizAttempts());
				attempts = attemptsQuiz.getNumberOfQuizAttempts();
				attempts++;
				
			}
			
			}
		
		Quiz quizNew = quiz.get();
		User userNew = user.get();
		
		if(attemptsQuiz != null) {
		attemptsQuiz.setQuiz(quizNew);
		attemptsQuiz.setUser(userNew);
		attemptsQuiz.setNumberOfQuizAttempts(attempts);
		return this.quizAttemptRepository.save(attemptsQuiz);
		
		} else {
		quizAttempt.setQuiz(quizNew);
		quizAttempt.setUser(userNew);
		quizAttempt.setNumberOfQuizAttempts(attempts);
		return this.quizAttemptRepository.save(quizAttempt);
		}
	}

	@Override
	public Long getAttempts(Long userId, Long quizId) {
		Optional<User> user = userRepository.findById(userId);
		
		
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		
		
		QuizAttempt attemptsQuiz =(QuizAttempt) this.quizAttemptRepository.findByUserAndQuiz(user, quiz);

		if(attemptsQuiz != null) {
		return attemptsQuiz.getNumberOfQuizAttempts();
		} else {
			return 0L;
		}
	}

	@Override
	public List<QuizAttempt> getAllAttempts(Long quizId) {
		
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		
		return this.quizAttemptRepository.findByQuiz(quiz);
	}

}
