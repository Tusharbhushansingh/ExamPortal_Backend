package com.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		User user = new User();
//		
//		user.setFirtsname("Tushar");
//		user.setLastname("");
//		user.setUsername("admin");
//		user.setPassword(this.bCryptPasswordEncoder.encode("admin"));
//		user.setEmail("admin@gmail.com");
//		user.setProfile("admin.pmg");
//		
//		Role role = new Role();
//		role.setRoleId(44l);
//		role.setRoleName("ADMIN");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUsername());
		
	}

	
	
}
