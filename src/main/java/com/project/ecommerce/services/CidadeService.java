package com.project.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.Cidade;

@Component
public interface CidadeService {
	
	List<Cidade> consultaCidadePorEstado(Long estadoId);
	
}
