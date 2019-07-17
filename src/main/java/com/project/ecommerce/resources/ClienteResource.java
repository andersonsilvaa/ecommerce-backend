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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.dto.ClienteDto;
import com.project.ecommerce.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorId(@PathVariable Long id) {
		Cliente cliente = this.service.consultaPorId(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorEmail(@RequestParam(value="value") String email) {
		Cliente cliente = this.service.consultaPorEmail(email);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@Valid @RequestBody ClienteDto objDto) {
		Cliente cliente = objDto.getDtoToEntity();
		cliente = this.service.salvar(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
											 .buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@Valid @RequestBody ClienteDto objDto, @PathVariable Long id) {
		Cliente cliente = objDto.getDtoToEntity(id);
		cliente = this.service.salvar(cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		this.service.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> consultaTodos() {
		List<ClienteDto> lstDto = this.service.consultaTodos();
		return ResponseEntity.ok().body(lstDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<?> consultaPorPaginacao(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="descricao") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<ClienteDto> lstDto = this.service.consultaPorPaginacao(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(lstDto);
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = this.service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}

}
