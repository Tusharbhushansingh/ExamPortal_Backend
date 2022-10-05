package com.exam.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.exam.model.quiz.QuizAttempt;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
public class User implements UserDetails{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String firtsname;
	private String lastname;
	private String email;
	private String phone;
	private boolean enabled=true;
	private String profile;
	
	//user many roles
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@JsonIgnore
	private Set<QuizAttempt> quizAttempts = new HashSet<>();
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long id, String username, String password, String firtsname, String lastname, String email,
			String phone, boolean enabled, String profile, Set<UserRole> userRoles, Set<QuizAttempt> quizAttempts) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firtsname = firtsname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.enabled = enabled;
		this.profile = profile;
		this.userRoles = userRoles;
		this.quizAttempts = quizAttempts;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirtsname() {
		return firtsname;
	}

	public void setFirtsname(String firtsname) {
		this.firtsname = firtsname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	public Set<QuizAttempt> getQuizAttempts() {
		return quizAttempts;
	}

	public void setQuizAttempts(Set<QuizAttempt> quizAttempts) {
		this.quizAttempts = quizAttempts;
	}
	
	//Security logic

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Set<Authority> set = new HashSet<>();
		this.userRoles.forEach(
				userRole -> {
					set.add(new Authority(userRole.getRole().getRoleName()));
				}
				);
		
		return set;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
}
