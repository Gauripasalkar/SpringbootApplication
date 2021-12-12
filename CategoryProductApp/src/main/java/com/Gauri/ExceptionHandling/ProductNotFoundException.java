package com.Gauri.ExceptionHandling;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

	private String exception="Product Not Found..Please Enter valid input Value";
	
	public ProductNotFoundException(String exception) {
		super(exception);
	}
	public String getException() {
		return exception;
	}
}
