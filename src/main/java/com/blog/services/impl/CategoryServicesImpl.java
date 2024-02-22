package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryServices;
import com.blog.entities.*;
import com.blog.exceptions.*;
@Service
public class CategoryServicesImpl implements CategoryServices {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category=this.modelMapper.map(categoryDto, Category.class);
		Category added=this.categoryRepo.save(category);
		return this.modelMapper.map(added, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category findCategory=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("category","categoryId",categoryId));
		findCategory.setCategoryTitle(categoryDto.getCategoryTitle());
		findCategory.setCategoryDescription(categoryDto.getCategoryDescription());
		this.categoryRepo.save(findCategory);
		return this.modelMapper.map(findCategory, CategoryDto.class);
	}

	@Override
	public void deteteCategory(int categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("category","categoryId",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(int categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResouceNotFoundException("Category","categoryId",categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> allcat=this.categoryRepo.findAll();
	List<CategoryDto>list=	allcat.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return list;
	}

}
