package com.Gauri.Product.Category;

import java.util.ArrayList;


import java.util.List;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Gauri.ExceptionHandling.CategoryNotFoundException;
import com.Gauri.Product.Category.*;



@RestController
@RequestMapping("/myApp")
@Validated
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/fetchcategories")
	public ResponseEntity<List<Category>> fetchCategories(){
		try {
			List<Category> users=categoryService.fetchAllCategories();
			return new ResponseEntity<List<Category>>(users,HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/fetchcategory/{id}")
	public ResponseEntity<Category> fetchCategoryById(@PathVariable(value="id") int id){
		
			Category category=categoryService.fetchCategoryById(id);
			if (category==null)
			      throw new CategoryNotFoundException("id-" + id);
			else
				return new ResponseEntity<Category>(category,HttpStatus.FOUND);
	}

	@GetMapping("/fetchcategory/categoryName")
	public ResponseEntity<Category> fetchCategoryByName(@RequestParam(value="name")  String userName){
		
			Category category=categoryService.fetchCategoryByName(userName);
			if (category==null)
			      throw new CategoryNotFoundException("name-" + userName);
			else
				return new ResponseEntity<Category>(category,HttpStatus.FOUND);
	}
	
	@PostMapping("/addcategory")
	public ResponseEntity<Category> addCategory( @Valid @RequestBody Category category) {
		try {
			category=categoryService.addCategory(category);
			return new ResponseEntity<Category>(category,HttpStatus.CREATED);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/editcategory/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable(value="id") int categoryId ,
										 @Valid @RequestBody Category category) {
		
			Category existCategory=categoryService.fetchCategoryById(categoryId);
			if(existCategory==null)
				throw new CategoryNotFoundException("id-" + categoryId);
			else {
				existCategory.setCategoryName(category.getCategoryName());
			
				Category update_category=categoryService.updateCategory(existCategory);
				return new ResponseEntity<Category>(update_category,HttpStatus.OK);
			}
	}
	
	@DeleteMapping("/deletecategory/{id}")
	public ResponseEntity<Category> deleteCategory(@PathVariable(value="id") int id) {
		Category category=null;
		category=categoryService.fetchCategoryById(id);
		
		if(category==null)
			throw new CategoryNotFoundException("id-" + id);
		else {
			categoryService.deleteCategory(category);
			return new ResponseEntity<Category>(category,HttpStatus.OK);
		}
	}
}

