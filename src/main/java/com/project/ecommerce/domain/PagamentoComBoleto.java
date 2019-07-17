package com.project.ecommerce.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.project.ecommerce.domain.enums.EstadoPagamento;

@Entity
@Table(name="PAGAMENTOCOMBOLETO")
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;
	
	@Column(name="DATA_VENCIMENTO")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	
	@Column(name="DATA_PAGAMENTO")
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public PagamentoComBoleto() {
	}	
	 
	public PagamentoComBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

}
