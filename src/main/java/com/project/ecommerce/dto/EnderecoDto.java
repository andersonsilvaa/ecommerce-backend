package com.project.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class EnderecoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String logradouro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String numero;
	
	private String complemento;
	
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cep;
	
	private ClienteDto cliente;
	
	private CidadeDto cidade;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public EnderecoDto() {
	}
	
	public EnderecoDto(Long id, String logradouro, String numero, String complemento, String bairro, String cep, ClienteDto cliente, CidadeDto cidade) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cliente = cliente;
		this.cidade = cidade;
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

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public CidadeDto getCidade() {
		return cidade;
	}
	
	public void setCidade(CidadeDto cidade) {
		this.cidade = cidade;
	}
	
	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", bairro=" + bairro + ", cep=" + cep + ", cliente=" + cliente + ", cidade=" + cidade
				+ "]";
	}
	
}
