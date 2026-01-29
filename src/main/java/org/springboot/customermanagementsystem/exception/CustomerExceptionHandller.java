package org.springboot.customermanagementsystem.exception;

import org.springboot.customermanagementsystem.entity.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerExceptionHandller {
	
	@ExceptionHandler(CustomerNotFunndException.class)
	public ResponseEntity<ResponseStructure<String>> handleCNFE(CustomerNotFunndException customerNotFunndException){
		ResponseStructure<String> structure = new ResponseStructure<>();
		
		structure.setMessage(customerNotFunndException.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData(null);
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}
