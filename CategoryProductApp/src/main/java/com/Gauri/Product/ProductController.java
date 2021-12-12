package com.Gauri.Product;

import java.util.ArrayList;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Gauri.ExceptionHandling.*;
import com.Gauri.Product.Category.Category;
import com.Gauri.Product.Category.CategoryService;

@RestController
@RequestMapping("/myApp")
@ControllerAdvice(annotations = RestController.class)
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/fetchproducts")
	public ResponseEntity<List<ProductDetails>> fetchProducts(){
		try {
			List<ProductDetails> products=productService.fetchAllProducts();
			return new ResponseEntity<List<ProductDetails>>(products,HttpStatus.FOUND);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/fetchproduct/{id}")
	public ResponseEntity<List<Object>> fetchCategoryByProductId(@PathVariable(value="id") int id){
		
			ProductDetails product=productService.fetchProductById(id);
			Category category=categoryService.fetchCategoryById(id);
			if (product==null)
			      throw new ProductNotFoundException("id-" + id);
			else {
				List<Object> newProductList = new ArrayList<>();
					if(product.getCategoryID()==category.getCategoryId()) {
						newProductList.add(product.getProductName());
						newProductList.add(category.getCategoryName());
						newProductList.add(product.getPrice());
				}
				return new ResponseEntity<List<Object>>(newProductList,HttpStatus.FOUND);
			}
	}
	
	@GetMapping("/fetchproduct/productName")
	public ResponseEntity<ProductDetails> fetchProductByName(@RequestParam(value="name")  String productName){
			ProductDetails product=productService.fetchProductByName(productName);
			if (product==null)
			      throw new ProductNotFoundException("name-" + productName);
			else
				return new ResponseEntity<ProductDetails>(product,HttpStatus.FOUND);
			
	}
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductDetails> addProduct(@RequestBody ProductDetails product) {
		try {
		    product=productService.addProduct(product);
			return new ResponseEntity<ProductDetails>(product,HttpStatus.CREATED);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/editproduct/{id}")
	public ResponseEntity<ProductDetails> updateProduct(@PathVariable(value="id") int productID ,
										 @RequestBody ProductDetails product) {
		
		try{
			ProductDetails existProduct=productService.fetchProductById(productID);
			
			existProduct.setProductName(product.getProductName());
			existProduct.setCategoryID(product.getCategoryID());
			existProduct.setPrice(product.getPrice());
		
			ProductDetails update_product=productService.updateProduct(existProduct);
			return new ResponseEntity<ProductDetails>(update_product,HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public ResponseEntity<ProductDetails> deleteProduct(@PathVariable(value="id") int id) {
		ProductDetails product=null;
		product=productService.fetchProductById(id);
		
		if(product==null)
			throw new ProductNotFoundException("id-" + id);
		else {
			productService.deleteProduct(product);
			return new ResponseEntity<ProductDetails>(product,HttpStatus.OK);
		}
	}
}
