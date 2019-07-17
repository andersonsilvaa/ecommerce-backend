package com.project.ecommerce.dto;

import java.io.Serializable;

import com.project.ecommerce.domain.Produto;

public class ProdutoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String nome;
	
	private Double preco;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public ProdutoDto() {
	}
	
	public ProdutoDto(Produto produto) {
		if(produto!=null) {
			this.id = produto.getId();
			this.nome = produto.getNome();
			this.preco = produto.getPreco();
		}
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
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Produto getDtoToEntity(Long... id) {
		if(id!=null) {
			for (Long idProduto : id) {
				this.id = idProduto;
				break;
			}
		}
		Produto produto = new Produto(this.id, this.nome, this.preco);
		return produto;
	}
	
}
