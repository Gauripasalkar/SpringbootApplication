package com.Gauri.Product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CategoryProductAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryProductAppApplication.class, args);
	}

}
