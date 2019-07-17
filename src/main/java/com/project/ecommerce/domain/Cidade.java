package com.project.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.ecommerce.dto.CidadeDto;

@Entity
@Table(name="CIDADE")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="ESTADO_ID")
	private Estado estado;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Cidade() {
	}
	
	public Cidade(Long id, String descricao, Estado estado) {
		this.id = id;
		this.descricao = descricao;
		this.estado = estado;
	}
	
	public Cidade(CidadeDto cidade) {
		if(cidade!=null) {
			this.id = cidade.getId();
			this.descricao = cidade.getDescricao();
			if(cidade.getEstado()!=null) {
				this.estado = new Estado(cidade.getEstado());
			}
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
	
	public Estado getEstado() {
		return estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	/*****************************************************
	 *	MÉTODO HASCODE E EQUALS
	 ****************************************************/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "Cidade [id=" + id + ", descricao=" + descricao + ", estado=" + estado + "]";
	}
	
}
