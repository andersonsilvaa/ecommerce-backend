package com.project.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.domain.Produto;
import com.project.ecommerce.dto.ProdutoDto;
import com.project.ecommerce.repositories.CategoriaRepository;
import com.project.ecommerce.repositories.ProdutoRepository;
import com.project.ecommerce.services.ProdutoService;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto consultaPorId(Long id) {
		Optional<Produto> Produto = this.produtoRepository.findById(id);
		return Produto.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<ProdutoDto> consultaPorPaginacao(String nome, 
											  List<Long> ids, 
											  Integer page, 
											  Integer linesPerPage, 
											  String orderBy, 
											  String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> lstCategoria = this.categoriaRepository.findAllById(ids);
		Page<Produto> lst = this.produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, lstCategoria, pageRequest);
		Page<ProdutoDto> lstDto = lst.map(obj -> new ProdutoDto(obj));
		return lstDto;
	}
	
}
