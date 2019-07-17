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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.ecommerce.dto.TelefoneDto;

@Entity
@Table(name="TELEFONE")
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NUMERO")
	private String numero;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Telefone() {
	}
	
	public Telefone(TelefoneDto telefone) {
		this.id = telefone.getId();
		this.numero = telefone.getNumero();
	}
	
	public Telefone(Long id, String numero, Cliente cliente) {
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
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
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
		Telefone other = (Telefone) obj;
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
		return "Telefone [cliente=" + cliente + ", numero=" + numero + "]";
	}

}
