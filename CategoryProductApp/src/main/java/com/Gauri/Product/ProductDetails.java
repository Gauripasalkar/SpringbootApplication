package com.Gauri.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.ManyToOne;

@Entity
@Table(name="productlist")
public class ProductDetails {

	@Id
	@Column(name="id")
	private int productID;
	
	@Column(name="productname")
	private String productName;
	
	@Column(name="categoryid")
	private int categoryID;
	
	@Column(name="price")
	private double price;
	
	public ProductDetails() {
		super();
	}

	/**
	 * @param productID
	 * @param productName
	 * @param categoryID
	 * @param price
	 */
	public ProductDetails(int productID, String productName, int categoryID, double price) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.categoryID = categoryID;
		this.price = price;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@ManyToOne
	private Category category;
}
