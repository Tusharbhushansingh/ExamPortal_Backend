package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.RoleRepository;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User local = this.userRepository.findByUsername(user.getUsername());
		System.out.println(local);
		if(local != null) {
			System.out.println("User is already exist!!");
			throw new Exception("User already present !!");
		} else {
			
			//user create
			for(UserRole ur : userRoles) {
				this.roleRepository.save(ur.getRole());
			}
			
			user.setUserRoles(userRoles);
			local = this.userRepository.save(user);
			
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
		
	}

	@Override
	public User updateUser(User user, String username) throws Exception {
		User user1 = this.userRepository.findByUsername(username);
		if(user1 != null) {
			if(user.getPassword() != null) {
				user1.setPassword(user.getPassword());
			}
			if(user.getFirtsname() != null) {
				user1.setFirtsname(user.getFirtsname());
			}
			if(user.getLastname() != null) {
				user1.setLastname(user.getLastname());
			}
			if(user.getEmail() != null) {
				user1.setEmail(user.getEmail());
			}
			if(user.getPhone() != null) {
				user1.setPhone(user.getPhone());
			}
			this.userRepository.save(user1);
			return user1;
		} else {
			throw new Exception("Username not exist !!");
		}
	}

}
