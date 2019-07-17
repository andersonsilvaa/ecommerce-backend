package com.project.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	/*****************************************************
	 *	CONSTRUTOR
	 ****************************************************/
	
	public EmailDto() {
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
