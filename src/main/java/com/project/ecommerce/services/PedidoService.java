package com.project.ecommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.Pedido;

@Component
public interface PedidoService {
	
	Pedido consultaPorId(Long id);

	Pedido salvar(Pedido pedido) ;
	
	Page<Pedido> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction);
	
}
