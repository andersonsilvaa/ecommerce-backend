package com.project.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.project.ecommerce.domain.enums.EstadoPagamento;

@Entity
@Table(name="PAGAMENTO")
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="ESTADO")
	private Integer estadoPagamento;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="PEDIDO_ID")
	@MapsId
	private Pedido pedido;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Pagamento() {
	}
	
	public Pagamento(Long id, EstadoPagamento estadoPagamento, Pedido pedido) {
		this.id = id;
		this.estadoPagamento = (estadoPagamento==null) ? null : estadoPagamento.getCodigo();
		this.pedido = pedido;
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

	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
		this.estadoPagamento = estadoPagamento.getCodigo();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
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
		return "Pagamento [id=" + id + ", estadoPagamento=" + estadoPagamento + ", pedido=" + pedido + "]";
	}
	
}
