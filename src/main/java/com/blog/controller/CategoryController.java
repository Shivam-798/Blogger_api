package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.payloads.*;
import com.blog.services.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import java.util.*;
@RestController
@RequestMapping("/api/categories")
@SecurityRequirement(name="scheme1")
public class CategoryController {
	
	@Autowired
	private CategoryServices categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory( @Valid @RequestBody CategoryDto categoryDto,@PathVariable int categoryId)
	{
		CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId)
	{
		this.categoryService.deteteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted is successfully!!",true),HttpStatus.OK);
	}
	
	@GetMapping("/{CatId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int CatId){
		CategoryDto categoryDto=this.categoryService.getCategory(CatId);
		return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		System.out.println("this is fire");
		List<CategoryDto> list=this.categoryService.getAllCategory();
//		return new ResponseEntity<List<CategoryDto>>(list,HttpStatus.OK);
		return ResponseEntity.ok(list);
	}
	
}
