package com.project.ecommerce.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.ecommerce.domain.Pedido;
import com.project.ecommerce.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorId(@PathVariable Long id) {
		Pedido pedido = this.service.consultaPorId(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@Valid @RequestBody Pedido obj) {
		obj = this.service.salvar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
											 .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorPaginacao(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="instante") String orderBy,
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Pedido> lst = this.service.consultaPorPaginacao(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(lst);
	}

}
