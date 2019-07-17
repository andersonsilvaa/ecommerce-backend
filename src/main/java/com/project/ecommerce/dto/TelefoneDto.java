package com.project.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class TelefoneDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;
	
	private ClienteDto cliente;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public TelefoneDto() {
	}
	
	public TelefoneDto(Long id, String numero, ClienteDto cliente) {
		this.id = id;
		this.numero = numero;
		this.cliente = cliente;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public ClienteDto getCliente() {
		return cliente;
	}
	
	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "TelefoneDto [id=" + id + ", numero=" + numero + ", cliente=" + cliente + "]";
	}

}
