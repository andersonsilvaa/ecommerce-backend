package com.project.ecommerce.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.project.ecommerce.domain.enums.EstadoPagamento;

@Entity
@Table(name="PAGAMENTOCOMCARTAO")
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@Column(name="NUMERO_DE_PARCELAS")
	private Integer numeroDeParcelas;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
