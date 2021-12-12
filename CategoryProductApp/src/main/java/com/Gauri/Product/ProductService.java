package com.Gauri.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ProductService {
	@Autowired
	ProductRepository productRep;
	
	
	public List<ProductDetails> fetchAllProducts(){
		return productRep.findAll();
	}
	
	public ProductDetails fetchProductById(int id) {
		List<ProductDetails> products=productRep.findAll();
		ProductDetails product=null;
		
		for(ProductDetails pro:products) {
			if(pro.getProductID()==id)
				product=pro;
		}
		return product;
	}
	
	public  ProductDetails fetchProductByName(String productName) {
		List<ProductDetails> products=productRep.findAll();
		ProductDetails product=null;
		
		
		for(ProductDetails pro:products) {
			if(pro.getProductName().equalsIgnoreCase(productName))
				product=pro;
		}
		return product;
	}
	
	public ProductDetails addProduct(ProductDetails product) {
		
		int id=getMaxId();
		product.setProductID(id);
		productRep.save(product);
		productRep.getById(id);
 		return product;
	}
	
	public int getMaxId() {
		return productRep.findAll().size()+1;
	}
	
	public ProductDetails updateProduct(ProductDetails product) {
		
		productRep.save(product);
		return product;
		
	}
	public void deleteProduct(ProductDetails product) {
		productRep.delete(product);
	}
}
