package com.project.ecommerce.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.domain.Cidade;
import com.project.ecommerce.domain.Estado;
import com.project.ecommerce.dto.CidadeDto;
import com.project.ecommerce.dto.EstadoDto;
import com.project.ecommerce.services.CidadeService;
import com.project.ecommerce.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> consultaTodos() {
		List<Estado> lst = this.service.consultaTodos();
		List<EstadoDto> lstDto = lst.stream().map(obj -> new EstadoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lstDto);
	}
	
	@RequestMapping(value="/{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDto>> consultaCidadesPorEstado(@PathVariable Long estadoId) {
		List<Cidade> lst = this.cidadeService.consultaCidadePorEstado(estadoId);
		List<CidadeDto> listDto = lst.stream().map(obj -> new CidadeDto(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
}
