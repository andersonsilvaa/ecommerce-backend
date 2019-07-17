package com.project.ecommerce.services.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.PagamentoComBoleto;
import com.project.ecommerce.services.BoletoService;

@Service
public class BoletoServiceImpl implements BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date dataDoPedido) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(calendar.getTime());
	}
	
}
