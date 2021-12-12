package com.Gauri.ExceptionHandling;

import java.util.HashMap;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status,WebRequest request){
		
		Map<String,Object>body=new HashMap<>();
		List<String> errors =ex.getBindingResult()
		.getFieldErrors()
		.stream()
		.map(x -> x.getDefaultMessage())
		.collect(Collectors.toList());
		
		body.put("errors", errors);
		return new ResponseEntity<>(body,headers,status);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	ResponseEntity<String> handleConstraintsValidationException(ConstraintViolationException ex){
		return new ResponseEntity<>("Error:"+ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)  
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleProductNotFoundExceptions(ProductNotFoundException ex, WebRequest request)  
	{   
		return new ResponseEntity<>("Error:"+ex.getException(),HttpStatus.NOT_FOUND); 
	}  
	
	@ExceptionHandler(CategoryNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(CategoryNotFoundException ex, WebRequest request)  
	{   
		return new ResponseEntity<>("Error:"+ex.getException(),HttpStatus.NOT_FOUND); 
	}   
}
