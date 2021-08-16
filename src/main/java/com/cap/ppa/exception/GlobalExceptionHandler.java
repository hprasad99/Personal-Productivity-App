package com.cap.ppa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	 public ResponseEntity<Object> exception(Exception exception){
		
		ErrorMessage errorMessage=new ErrorMessage(exception.getMessage(),404);
		return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
		
	}
}
