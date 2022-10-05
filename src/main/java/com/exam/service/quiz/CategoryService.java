package com.exam.service.quiz;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.quiz.Category;

@Service
public interface CategoryService {

	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public Set<Category> getCateories();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCatetory(Long categoryId);
	
}
