package com.project.ecommerce.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.dto.CategoriaDto;
import com.project.ecommerce.repositories.CategoriaRepository;
import com.project.ecommerce.services.CategoriaService;
import com.project.ecommerce.services.exceptions.DataIntegrityException;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria consultaPorId(Long id) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	@Transactional
	public Categoria salvar(Categoria categoria) {
		if(categoria.getId()!=null) {
			Categoria obj = this.consultaPorId(categoria.getId());
			this.atualizar(obj, categoria);
		}
		return this.categoriaRepository.save(categoria);
	}

	private void atualizar(Categoria obj, Categoria categoria) {
		obj.setNome(categoria.getNome());
	}

	public void excluir(Long id) {
		this.consultaPorId(id);
		try {
			this.categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}

	public List<CategoriaDto> consultaTodos() {
		List<Categoria> lst = this.categoriaRepository.findAll();
		List<CategoriaDto> lstDto = lst.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
		return lstDto;
	}
	
	public Page<CategoriaDto> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> lst = this.categoriaRepository.findAll(pageRequest);
		Page<CategoriaDto> lstDto = lst.map(obj -> new CategoriaDto(obj));
		return lstDto;
	}
	
}
