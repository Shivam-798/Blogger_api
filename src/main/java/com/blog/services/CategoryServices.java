package com.blog.services;
import com.blog.payloads.*;
import java.util.*;
public interface CategoryServices {
//create
	CategoryDto createCategory(CategoryDto categoryDto);
	//update
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	//delete
	void deteteCategory(int categoryId);
	//get
	CategoryDto getCategory(int categoryId);
	//get All
	
	List<CategoryDto> getAllCategory();
}
//interface mein sab kuch bydefault public hota hai