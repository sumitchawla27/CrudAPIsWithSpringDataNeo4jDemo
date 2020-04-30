package com.example.crudapiswithneo4j.exceptionHandling;

public class ResourceNotFoundException extends RuntimeException{

	private String message;

	public ResourceNotFoundException(String message){
		super(message);
	}
}
