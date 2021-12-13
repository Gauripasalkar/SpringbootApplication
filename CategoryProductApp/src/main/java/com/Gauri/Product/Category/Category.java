package com.Gauri.Product.Category;

import javax.persistence.Column;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import org.springframework.stereotype.Component;

@Entity
@Table(name="categorylist")
public class Category {
	
	@Id
	@Column(name="categoryid")
	private int categoryID;
	
	@Column(name="categoryname")
	private String categoryName;
	
	public Category() {
		super();
	}

	public Category(int categoryID, String name) {
		super();
		this.categoryID = categoryID;
		this.categoryName = name;
	}

	public int getCategoryId() {
		return categoryID;
	}


	public void setCategoryId(int userId) {
		this.categoryID = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String name) {
		this.categoryName = name;
	}
	@OneToMany(cascade=CascadeType.ALL, mappedBy="category")
	private List<ProductDetails> products;
	

}
