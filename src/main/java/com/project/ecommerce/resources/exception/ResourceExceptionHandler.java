package com.project.ecommerce.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.project.ecommerce.services.exceptions.AuthorizationException;
import com.project.ecommerce.services.exceptions.DataIntegrityException;
import com.project.ecommerce.services.exceptions.FileException;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<Error> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<Error> dataIntegrity(DataIntegrityException exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException exception, HttpServletRequest request){
		
		ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", exception.getMessage(), request.getRequestURI());
		for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<Error> authorization(AuthorizationException exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<Error> file(FileException exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<Error> amazonService(AmazonServiceException exception, HttpServletRequest request){
		HttpStatus status = HttpStatus.valueOf(exception.getErrorCode());
		Error error = new Error(System.currentTimeMillis(), status.value(), "Erro Amazon Service", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<Error> amazonClient(AmazonClientException exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon Client", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<Error> amazonS3(AmazonS3Exception exception, HttpServletRequest request){
		
		Error error = new Error(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro Amazon S3", exception.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
