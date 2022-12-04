package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.EmailDetails;
import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import com.exam.service.impl.EmailServiceImpl;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	//creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		Set<UserRole> roles = new HashSet<>();
		
		//encoding password  with bcryptpasswordencoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		roles.add(userRole);
		
		return this.userService.createUser(user, roles);
		
		}
	
	//Get user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	//Delete user
	@DeleteMapping("/{id}")
	public void removeUser(@PathVariable("id") Long id) {
		this.userService.deleteUser(id);
	}
	
	
	//Update user
	@PutMapping("/{username}")
	public User updateUser(@RequestBody User user, @PathVariable("username") String userName) throws Exception {
		return this.userService.updateUser(user, userName);
	}
	
	//forget password
	@PostMapping("/forget/")
	public void forgetPassowrd(@RequestBody String email) {
		System.out.println(email);
		String otp = this.userService.sendOtp(email);
		if(otp != null) {
			EmailDetails emailDetails = new EmailDetails();
			emailDetails.setRecipient(email);
			emailDetails.setSubject("Otp from Exam portal");
			emailDetails.setMsgBody("OTP =" + otp);

		String status = this.emailServiceImpl.sendSimpleMail(emailDetails);
		System.out.println(status);
		}
	}
	
	//verify Otp
	@GetMapping("/verifyotp/{otp}/{email}")
	public Boolean verifyOtp(@PathVariable String otp, @PathVariable String email) {
		System.out.println(email);
		Boolean verify_otp = this.userService.verifyOtp(otp, email);
		System.out.println(verify_otp);
		return verify_otp;
	}
	
	//change password
	@PutMapping("/changepassword/{password}/{email}")
	public void passwordChange(@PathVariable String password, @PathVariable String email) {
		this.userService.changePassword(password, email);
	}
}
