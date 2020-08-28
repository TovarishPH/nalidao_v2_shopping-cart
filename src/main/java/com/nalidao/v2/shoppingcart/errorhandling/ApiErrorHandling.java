package com.nalidao.v2.shoppingcart.errorhandling;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;
import com.nalidao.v2.shoppingcart.errorhandling.utils.ApiErrorDetails;

@ControllerAdvice
public class ApiErrorHandling {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ApiErrorDetails> handleProductNotFoundException(ProductNotFoundException e) {
		ApiErrorDetails details =  new ApiErrorDetails("ProductNotFoundException",
														HttpStatus.NOT_FOUND.toString(),
														e.getLocalizedMessage(), 
														e.getClass().getPackage().toString(), 
														LocalDateTime.now());
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
}
