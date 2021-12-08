package com.example.airfrance.challenge.exception;

import java.util.Date;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Common class to handle all exception
 * 
 * @author zhaimi
 * @Date 05/12/2021
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	// handle specific exceptions
	/**
	 * Method to handle Resource not found Exception
	 * 
	 * @param exception ResourceNotFoundException
	 * @param request
	 * @return Error Manager object with status not found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		ErrorManager errorManager = new ErrorManager(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorManager, HttpStatus.NOT_FOUND);
	}

	// handle global exceptions
	/**
	 * Method to handle Exception
	 * 
	 * @param exception
	 * @param request
	 * @return Error Manager object with status internal server error
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
		ErrorManager errorManager = new ErrorManager(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity(errorManager, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// handle custom validation error
	/**
	 * Method to handle custum validation error exception
	 * 
	 * @param exception MethodArgumentNotValidException
	 * @return Error Manager object with status Bad request
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationError(MethodArgumentNotValidException exception) {
		ErrorManager errorManager = null;
		FieldError fieldException = exception.getBindingResult().getFieldError();
		// if field validation exception
		if (fieldException != null)
			errorManager = new ErrorManager(new Date(), "Validation error", fieldException.getDefaultMessage());
		// if other ( like validation of adult french
		else
			errorManager = new ErrorManager(new Date(), "Validation error",
					exception.getBindingResult().getGlobalError().getDefaultMessage());

		return new ResponseEntity(errorManager, HttpStatus.BAD_REQUEST);
	}
}
