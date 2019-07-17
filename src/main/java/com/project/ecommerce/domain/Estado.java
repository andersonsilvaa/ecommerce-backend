package com.project.ecommerce.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.dto.EstadoDto;

@Entity
@Table(name="ESTADO")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@JsonIgnore
	@OneToMany(mappedBy="estado", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private List<Cidade> cidades;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Estado() {
	}
	
	public Estado(Long id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public Estado(EstadoDto estado) {
		this.id = estado.getId();
		this.descricao = estado.getDescricao();
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
	
	public List<Cidade> getCidades() {
		if(this.cidades==null) {
			this.cidades = new ArrayList<Cidade>();
		}
		return Collections.unmodifiableList(cidades);
	}
	
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public void addCidade(Cidade cidade) {
		if(this.cidades==null) {
			this.cidades = new ArrayList<Cidade>();
		}
		if(cidade!=null) {
			cidade.setEstado(this);
			this.cidades.add(cidade);
		}
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
		Estado other = (Estado) obj;
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
		return "Estado [id=" + id + ", descricao=" + descricao + ", cidades=" + cidades + "]";
	}
	
}
