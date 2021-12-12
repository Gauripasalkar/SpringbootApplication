package com.Gauri.Product.Category;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRep;
	
	
	public List<Category> fetchAllCategories(){
		return categoryRep.findAll();
	}
	
	public Category fetchCategoryById(int id) {
		List<Category> categories=categoryRep.findAll();
		Category category=null;
		
		for(Category i:categories) {
			if(i.getCategoryId()==id)
				category=i;
		}
		return category;
	}
	
	public Category fetchCategoryByName(String name) {
		List<Category> categories=categoryRep.findAll();
		Category category=null;
		
		
		for(Category i:categories) {
			if(i.getCategoryName().equalsIgnoreCase(name))
				category=i;
		}
		return category;
	}
	
	public Category addCategory(Category category) {
		
		int id=getMaxId();
		category.setCategoryId(id);
		categoryRep.save(category);
		categoryRep.getById(id);
 		return category;
	}
	
	public int getMaxId() {
		return categoryRep.findAll().size()+1;
	}
	
	public Category updateCategory(Category category) {
		categoryRep.save(category);
		return category;
	}
	
	public void deleteCategory(Category category) {
		categoryRep.delete(category);
	}
}
