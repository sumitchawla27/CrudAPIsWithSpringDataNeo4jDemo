package com.example.crudapiswithneo4j.exceptionHandling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
public class AppExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);


	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handleException(HttpMessageNotReadableException e) {
		LOGGER.error("Invalid Request JSON ", e);
		return e.getMessage();
	}

	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String handleException(MissingServletRequestParameterException e) {
		LOGGER.error("Invalid Request Parameters ", e);
		return e.getMessage();
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	String handleException(HttpRequestMethodNotSupportedException e) {
		LOGGER.error("Invalid Http Method ", e);
		return e.getMessage();
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String handleException(Exception e) {
		LOGGER.error("Internal Error ", e);
		return e.getMessage();
	}

	@ExceptionHandler(value = ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String handleResourceNotFoundException(ResourceNotFoundException exception) {
		LOGGER.error("Resource Not found Exception {}", exception.getMessage());
		return exception.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	List<ObjectError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		LOGGER.error("MethodArgumentNotValidException {}", ex.getMessage());
		return ex.getBindingResult().getAllErrors();
	}


}
