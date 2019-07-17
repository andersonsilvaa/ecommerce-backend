package com.project.ecommerce.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends Error {

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors;
	
	/*****************************************************
	 *	CONSTRUTOR
	 ****************************************************/
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public List<FieldMessage> getErrors() {
		if(errors==null) {
			this.errors = new ArrayList<FieldMessage>();
		}
		return errors;
	}

	public void addError(String fieldName, String message) {
		if(this.errors==null) {
			this.errors = new ArrayList<FieldMessage>();
		}
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
