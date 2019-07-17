package com.project.ecommerce.services;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.PagamentoComBoleto;

@Component
public interface BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date dataDoPedido);
	
}
