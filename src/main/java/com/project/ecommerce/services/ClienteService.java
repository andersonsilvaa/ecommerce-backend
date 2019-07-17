package com.project.ecommerce.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.dto.ClienteDto;

@Component
public interface ClienteService {
	
	Cliente consultaPorId(Long id);
	
	Cliente salvar(Cliente cliente);

	void excluir(Long id);

	List<ClienteDto> consultaTodos();
	
	Page<ClienteDto> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction);
	
	URI uploadProfilePicture(MultipartFile multipartFile);
	
	Cliente consultaPorEmail(String email);
	
}
