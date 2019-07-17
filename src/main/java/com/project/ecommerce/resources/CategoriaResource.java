package com.project.ecommerce.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.dto.CategoriaDto;
import com.project.ecommerce.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorId(@PathVariable Long id) {
		Categoria categoria = this.service.consultaPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@Valid @RequestBody CategoriaDto objDto) {
		Categoria categoria = objDto.getDtoToEntity();
		categoria = this.service.salvar(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
											 .buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@Valid @RequestBody CategoriaDto objDto, @PathVariable Long id) {
		Categoria categoria = objDto.getDtoToEntity(id);
		categoria = this.service.salvar(categoria);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		this.service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> consultaTodos() {
		List<CategoriaDto> lstDto = this.service.consultaTodos();
		return ResponseEntity.ok().body(lstDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorPaginacao(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<CategoriaDto> lstDto = this.service.consultaPorPaginacao(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(lstDto);
	}

}
