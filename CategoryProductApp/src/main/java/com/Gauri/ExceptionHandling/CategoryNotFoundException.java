package com.Gauri.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {
	
	private String exception="Category Not Found..Please Enter valid input Value";
	
	public CategoryNotFoundException(String exception) {
		super(exception);
	}
	public String getException() {
		return exception;
	}
}
