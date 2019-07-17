package com.project.ecommerce.dto;

import java.io.Serializable;

import com.project.ecommerce.domain.Estado;

public class EstadoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public EstadoDto() {
	}
	
	public EstadoDto(Estado estado) {
		if(estado!=null) {
			this.id = estado.getId();
			this.descricao = estado.getDescricao();
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "EstadoDto [id=" + id + ", descricao=" + descricao + "]";
	}
	
}
