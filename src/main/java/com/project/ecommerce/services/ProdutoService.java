package com.project.ecommerce.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.Produto;
import com.project.ecommerce.dto.ProdutoDto;

@Component
public interface ProdutoService {
	
	public Produto consultaPorId(Long id);
	
	public Page<ProdutoDto> consultaPorPaginacao(String nome, 
												 List<Long> ids, 
												 Integer page, 
												 Integer linesPerPage, 
												 String orderBy, 
												 String direction);
	
}
