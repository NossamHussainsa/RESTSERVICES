package com.nt.test.GloBalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nt.test.Exception.CustomException;

@RestControllerAdvice
public class GloBalExceptionHandling {
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String>showException(Exception ex){
		 return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<String>showCustomException(CustomException ex){
		 return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);	
	}
}
