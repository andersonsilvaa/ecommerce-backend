package com.project.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.Cidade;
import com.project.ecommerce.repositories.CidadeRepository;
import com.project.ecommerce.services.CidadeService;

@Service
public class CidadeServiceImpl implements CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public List<Cidade> consultaCidadePorEstado(Long estadoId) {
		return this.cidadeRepository.findCidades(estadoId);
	}

	
	
}
