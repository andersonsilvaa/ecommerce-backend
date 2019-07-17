package com.project.ecommerce.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.Estado;
import com.project.ecommerce.repositories.EstadoRepository;
import com.project.ecommerce.services.EstadoService;

@Service
public class EstadoServiceImpl implements EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Override
	public List<Estado> consultaTodos() {
		return this.estadoRepository.findAllByOrderByDescricao();
	}
	
	
}
