package com.blog.exceptions;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blog.payloads.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResouceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExcptionHandler( ResouceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> hadleMehodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String ,String>resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> apiExceptionHandler(ApiException ex){
		String message=ex.getMessage();
		//boolean success=false;
		
		ApiResponse response=new ApiResponse(message,false);
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST);
	}
}
