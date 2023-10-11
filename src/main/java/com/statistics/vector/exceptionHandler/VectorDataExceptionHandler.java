package com.statistics.vector.exceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.statistics.vector.exceptions.VectorNotFoundException;

/**
 * Class providing exception handling for the application
 *
 */
@ControllerAdvice
public class VectorDataExceptionHandler {

	 private final Logger logger = LoggerFactory.getLogger(VectorDataExceptionHandler.class);

	/**
	 * Exception handler for incorrect vector ID
	 * 
	 * @param VectorNotFoundException
	 * @return ResponseEntity
	 */
	@ExceptionHandler(VectorNotFoundException.class)
	public ResponseEntity<String> handleVectorNotFoundException(VectorNotFoundException ex) {
	       logger.error("exception occurred: {}", ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
}
