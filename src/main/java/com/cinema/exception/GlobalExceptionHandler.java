package com.cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
	@ExceptionHandler(MovieNotFoundException.class)
	public ProblemDetail handleMovieNotFoundException(MovieNotFoundException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(FileExistsException.class)
	public ProblemDetail handlerFileExistsException(FileExistsException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	@ExceptionHandler(EmptyFileException.class)
	public ProblemDetail handlerEmptyFileException(EmptyFileException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
	}
	
	
	
}
