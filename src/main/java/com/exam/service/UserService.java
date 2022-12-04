package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;

@Service
public interface UserService {

	//creating user;
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public User getUser(String username);
	
	//delete by user by id
	public void deleteUser(Long id);
	
	//update user
	public User updateUser(User user, String username) throws Exception;
	
	//forget password logic
	public String sendOtp(String email);
	
	//verify Otp
	public Boolean verifyOtp(String otp, String email);
	
	//Change the password
	public void changePassword(String password, String email);
	
}
