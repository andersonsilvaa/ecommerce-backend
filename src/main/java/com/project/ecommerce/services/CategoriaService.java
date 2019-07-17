package com.project.ecommerce.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.dto.CategoriaDto;

@Component
public interface CategoriaService {
	
	public Categoria consultaPorId(Long id);

	public Categoria salvar(Categoria categoria);

	public void excluir(Long id);

	public List<CategoriaDto> consultaTodos();
	
	public Page<CategoriaDto> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction);
	
}
