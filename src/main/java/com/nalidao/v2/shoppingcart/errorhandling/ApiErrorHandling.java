package com.nalidao.v2.shoppingcart.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nalidao.v2.shoppingcart.errorhandling.exception.ProductNotFoundException;
import com.nalidao.v2.shoppingcart.errorhandling.exception.ShoppingCartNotFoundException;
import com.nalidao.v2.shoppingcart.errorhandling.utils.ApiErrorDetails;
import com.nalidao.v2.shoppingcart.errorhandling.utils.ApiFormErrorDetails;

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
	
	@ExceptionHandler(ShoppingCartNotFoundException.class)
	public ResponseEntity<ApiErrorDetails> handleShoppingCartNotFoundException(ShoppingCartNotFoundException e) {
		ApiErrorDetails details = new ApiErrorDetails("ShoppingCartNotFound", 
														HttpStatus.NOT_FOUND.toString(), 
														e.getLocalizedMessage(), 
														e.getClass().getPackage().toString(), 
														LocalDateTime.now());
		
		return new ResponseEntity<ApiErrorDetails>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ApiFormErrorDetails>> handleFormArgumentNotValid(MethodArgumentNotValidException e) {
		List<ApiFormErrorDetails> detailList = new ArrayList<ApiFormErrorDetails>();
		
		List<FieldError> fields = e.getBindingResult().getFieldErrors();
		fields.forEach(f -> {
			ApiFormErrorDetails detail = new ApiFormErrorDetails(f.getField(), f.getDefaultMessage());
			detailList.add(detail);
		});
		
		return new ResponseEntity<List<ApiFormErrorDetails>>(detailList, HttpStatus.BAD_REQUEST);
	}
}
