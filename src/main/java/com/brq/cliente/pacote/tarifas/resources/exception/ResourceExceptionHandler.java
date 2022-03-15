package com.brq.cliente.pacote.tarifas.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.brq.cliente.pacote.tarifas.services.exception.DataIntegrityException;
import com.brq.cliente.pacote.tarifas.services.exception.ObjectNotFoundException;



@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
		//valida os campos
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
			//ValidationError tem uma lista de FieldMessage
			ValidationError err = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de validação");//cada erro que existir na lista de MethodArgumentNotValidException vai gerar o FieldMessagem
			//acessa todos os campos que tiveram erro na excecao MethodArgumentNotValidException e add no construtor FieldMessagem
			for(FieldError x : e.getBindingResult().getFieldErrors()) {
				err.addError(x.getField(), x.getDefaultMessage());
			}
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}

}
