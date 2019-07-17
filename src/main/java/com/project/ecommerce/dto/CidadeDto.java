package com.project.ecommerce.dto;

import java.io.Serializable;

import com.project.ecommerce.domain.Cidade;

public class CidadeDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricao;
	private EstadoDto estado;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public CidadeDto() {
	}
	
	public CidadeDto(Cidade cidade) {
		if(cidade!=null) {
			this.id = cidade.getId();
			this.descricao = cidade.getDescricao();
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
	
	public EstadoDto getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoDto estado) {
		this.estado = estado;
	}

	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CidadeDto [id=");
		builder.append(id);
		builder.append(", descricao=");
		builder.append(descricao);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}
	
}
