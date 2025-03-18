package com.mayank.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(BlogNotFoundException ex) 
	{
        Map<String, String> response = new HashMap<>();
        response.put("Error Message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex, WebRequest request) 
	{
        ExceptionResponse response = new ExceptionResponse(
                LocalDateTime.now(),
                "An unexpected error occurred, Sorry for inconvenience caused!",
                request.getDescription(false)
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) 
	{
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidDataException(InvalidDataException ex, WebRequest request) 
	{
	    ExceptionResponse response = new ExceptionResponse(
	            LocalDateTime.now(),
	            ex.getMessage(),
	            request.getDescription(false)
	    );
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
