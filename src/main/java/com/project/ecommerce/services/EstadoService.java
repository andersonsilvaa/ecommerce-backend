package com.project.ecommerce.services;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.Estado;

@Component
public interface EstadoService {
	
	List<Estado> consultaTodos();
	
}
