package com.cg.hms.exceptions;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(NoSuchElementException exe) {
		ErrorResponse response = new ErrorResponse(new Date(),exe.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(DuplicateEntryException.class)
	public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntryException exe) {
		ErrorResponse response = new ErrorResponse(new Date(),exe.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoEntryException.class)
	public ResponseEntity<ErrorResponse> handleNoEntryException(NoEntryException exe) {
		
		ErrorResponse response = new ErrorResponse(new Date(),exe.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(NoRecordsException.class)
	public ResponseEntity<ErrorResponse> handleNoRecordsException(NoRecordsException exe) {
		
		ErrorResponse response = new ErrorResponse(new Date(),exe.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationFailedException.class)
	public ResponseEntity<ErrorResponse> handleValidationFailed(ValidationFailedException exe) {
		
		ErrorResponse response = new ErrorResponse(new Date(),exe.getMessage(),HttpStatus.NOT_FOUND.toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
}
