package com.project.ecommerce.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.project.ecommerce.domain.Categoria;

public class CategoriaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public CategoriaDto() {
	}

	public CategoriaDto(Categoria categoria) {
		if(categoria!=null) {
			this.setId(categoria.getId());
			this.setNome(categoria.getNome());
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
	
	public Categoria getDtoToEntity(Long... id) {
		if(id!=null) {
			for (Long idCategoria : id) {
				this.id = idCategoria;
				break;
			}
		}
		Categoria categoria = new Categoria(this.id, this.nome);
		return categoria;
	}
	
}
